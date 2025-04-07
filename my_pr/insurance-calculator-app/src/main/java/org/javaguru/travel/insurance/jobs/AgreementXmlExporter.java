package org.javaguru.travel.insurance.jobs;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreCommand;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.services.TravelExportAgreementToXmlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgreementXmlExporter {

    private static final Logger logger = LoggerFactory.getLogger(AgreementXmlExporter.class);

    private final TravelExportAgreementToXmlService agreementToXmlService;

    public void exportAgreement(String uuid) {
        logger.info("AgreementXmlExporterJob started for uuid = " + uuid);
        agreementToXmlService.export(new TravelExportAgreementToXmlCoreCommand(uuid));
        logger.info("AgreementXmlExporterJob finished for uuid = " + uuid);
    }

}
