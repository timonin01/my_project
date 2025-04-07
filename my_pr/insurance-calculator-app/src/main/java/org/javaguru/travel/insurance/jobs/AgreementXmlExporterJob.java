package org.javaguru.travel.insurance.jobs;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import org.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.services.TravelGetAgreementService;
import org.javaguru.travel.insurance.core.services.TravelGetNotExportedAgreementUuidsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgreementXmlExporterJob {

    private static final Logger logger = LoggerFactory.getLogger(AgreementXmlExporterJob.class);

    @Value("${agreement.xml.exporter.job.thread.count}")
    private Integer threadCount;

    @Value( "${agreement.xml.exporter.job.enabled:false}" )
    private boolean jobEnabled;

    private final TravelGetNotExportedAgreementUuidsService getAgreementUuidsService;
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
        TravelGetNotExportedAgreementUuidsCoreResult coreResult = getAgreementUuidsService.getAllAgreement(
                new TravelGetNotExportedAgreementUuidsCoreCommand()
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

    private void exportAgreement(String uuid) {
        logger.info("AgreementXmlExporterJob started for uuid = " + uuid);
        agreementXmlExporter.exportAgreement(uuid);
        logger.info("AgreementXmlExporterJob finished for uuid = " + uuid);
    }

    private AgreementDTO getAgreement(String uuid){
        TravelGetAgreementCoreResult coreResult  = getAgreementService.getAgreement(
                new TravelGetAgreementCoreCommand(uuid)
        );
        return coreResult.getAgreement();
    }


}
