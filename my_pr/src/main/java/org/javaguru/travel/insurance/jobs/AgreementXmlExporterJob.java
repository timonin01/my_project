package org.javaguru.travel.insurance.jobs;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementUuidsCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementUuidsCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.services.TravelGetAgreementService;
import org.javaguru.travel.insurance.core.services.TravelGetAgreementUuidsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgreementXmlExporterJob {

    private static final Logger logger = LoggerFactory.getLogger(AgreementXmlExporterJob.class);

    @Value( "${agreement.xml.exporter.job.enabled:false}" )
    private boolean jobEnabled;

    @Value( "${file.export.path}" )
    private String agreementExportPath;

    private final TravelGetAgreementUuidsService getAgreementUuidsService;
    private final TravelGetAgreementService getAgreementService;

    private final XmlMapper xmlMapper = new XmlMapper();

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void doJob() {
        if(jobEnabled) {
            executeJob();
        }
    }

    private void executeJob(){
        logger.info("AgreementXmlExporterJob started");
        List<String> allAgreementUuids = getAllAgreementUuids();
        allAgreementUuids.forEach(this::exportAgreement);
        logger.info("AgreementXmlExporterJob finished");
    }

    private List<String> getAllAgreementUuids(){
        TravelGetAgreementUuidsCoreResult coreResult = getAgreementUuidsService.getAllAgreement(
                new TravelGetAgreementUuidsCoreCommand()
        );
        return coreResult.getAgreementUuids();
    }

    private void exportAgreement(String uuid){
        logger.info("AgreementXmlExporterJob started for uuid = " + uuid);
        AgreementDTO agreement = getAgreement(uuid);
        try {
            String agreementXml = convertAgreementToXml(agreement);
            storeXmlToFile(uuid, agreementXml);
        }catch (Exception e) {
            logger.info("AgreementXmlExporterJob failed for agreement uuid = " + agreement.getUuid(), e);
        }
        logger.info("AgreementXmlExporterJob finished for uuid = " + uuid);
    }

    private AgreementDTO getAgreement(String uuid){
        TravelGetAgreementCoreResult coreResult  = getAgreementService.getAgreement(
                new TravelGetAgreementCoreCommand(uuid)
        );
        return coreResult.getAgreement();
    }

    private String convertAgreementToXml(AgreementDTO agreement) throws Exception {
        // Сериализуем объект в XML
        String xml = xmlMapper.writeValueAsString(agreement);
        return xml;
    }

    private void storeXmlToFile(String uuid, String agreementXml){
        try (FileWriter writer = new FileWriter(agreementExportPath)) {
            File file = new File(agreementExportPath + "/agreement-" + uuid + ".xml");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(agreementXml);
            bw.close();
            logger.info("XML has been successfully written to the file: " + agreementXml);
        } catch (IOException e) {
            logger.info("XML is not written to the file: " + uuid);
        }
    }

}
