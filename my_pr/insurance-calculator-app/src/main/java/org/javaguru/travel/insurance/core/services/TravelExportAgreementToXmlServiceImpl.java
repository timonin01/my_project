package  org.javaguru.travel.insurance.core.services;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreResult;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.domain.entities.AgreementXmlExportEntity;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementXmlExportEntityRepository;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelExportAgreementToXmlServiceImpl implements TravelExportAgreementToXmlService{

    @Value( "${file.export.path}" )
    private String agreementExportPath;

    private static final Logger logger = LoggerFactory.getLogger(TravelExportAgreementToXmlServiceImpl.class);

    private final XmlMapper xmlMapper = new XmlMapper();

    private final TravelGetAgreementService getAgreementService;
    private final AgreementXmlExportEntityRepository agreementXmlExportEntityRepository;
    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public TravelExportAgreementToXmlCoreResult export(TravelExportAgreementToXmlCoreCommand command){
        String uuid = command.getUuid();
        AgreementDTO agreement = getAgreement(uuid);
        try {
            String agreementXml = convertAgreementToXml(agreement);
            storeXmlToFile(uuid, agreementXml);
        }catch (Exception e){
            logger.info("XML is not written to the file: " + uuid);
            return new TravelExportAgreementToXmlCoreResult(List.of(
                    validationErrorFactory.buildError("ERROR_CODE_20")));
        }
        saveToDatabaseInfoAboutExportedAgreement(command);

        return new TravelExportAgreementToXmlCoreResult();
    }

    private void saveToDatabaseInfoAboutExportedAgreement(TravelExportAgreementToXmlCoreCommand command) {
        AgreementXmlExportEntity agreementXmlExportEntity = new AgreementXmlExportEntity();
        agreementXmlExportEntity.setAgreementUuid(command.getUuid());
        agreementXmlExportEntity.setAlreadyExported(Boolean.TRUE);
        agreementXmlExportEntityRepository.save(agreementXmlExportEntity);
    }

    public String convertAgreementToXml(AgreementDTO agreement) throws Exception {
        // Сериализуем объект в XML
        String xml = xmlMapper.writeValueAsString(agreement);
        return xml;
    }

    public void storeXmlToFile(String uuid, String agreementXml) throws Exception {
        FileWriter writer = new FileWriter(agreementExportPath);
        File file = new File(agreementExportPath + "/agreement-" + uuid + ".xml");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(agreementXml);
        bw.close();
        logger.info("XML has been successfully written to the file: " + agreementXml);
    }

    private AgreementDTO getAgreement(String uuid){
        TravelGetAgreementCoreResult coreResult  = getAgreementService.getAgreement(
                new TravelGetAgreementCoreCommand(uuid)
        );
        return coreResult.getAgreement();
    }

}
