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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgreementXmlExporterJob {

    private static final Logger logger = LoggerFactory.getLogger(AgreementXmlExporterJob.class);

    @Value("${agreement.xml.exporter.job.thread.count}")
    private int threadCount;

    @Value( "${agreement.xml.exporter.job.enabled:false}" )
    private boolean jobEnabled;

    private final TravelGetAgreementUuidsService getAgreementUuidsService;
    private final TravelGetAgreementService getAgreementService;

    private final AgreementXmlExporter agreementXmlExporter;

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void doJob() {
        if(jobEnabled) {
            executeJob();
        }
    }

    private void executeJob(){
        logger.info("AgreementXmlExporterJob started");
        List<String> allAgreementUuids = getAllAgreementUuids();
        exportAgreement(allAgreementUuids);
        logger.info("AgreementXmlExporterJob finished");
    }

    private List<String> getAllAgreementUuids(){
        TravelGetAgreementUuidsCoreResult coreResult = getAgreementUuidsService.getAllAgreement(
                new TravelGetAgreementUuidsCoreCommand()
        );
        return coreResult.getAgreementUuids();
    }

    private void exportAgreement(List<String> uuids){
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        for (String uuid : uuids){
            executor.submit(()->exportAgreement(uuid));
        }
        // Завершаем ExecutorService и ждем завершения всех задач
        executor.shutdown();
        try {
            // Ожидаем завершения всех задач в течение 1 часа
            if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
                // Если задачи не завершились, принудительно завершаем
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            // Если текущий поток был прерван, принудительно завершаем ExecutorService
            executor.shutdownNow();
            Thread.currentThread().interrupt(); // Восстанавливаем статус прерывания
        }
    }

    private void exportAgreement(String uuid){
        logger.info("AgreementXmlExporterJob started for uuid = " + uuid);
        AgreementDTO agreement = getAgreement(uuid);
        try {
            String agreementXml = agreementXmlExporter.convertAgreementToXml(agreement);
            agreementXmlExporter.storeXmlToFile(uuid, agreementXml);
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


}
