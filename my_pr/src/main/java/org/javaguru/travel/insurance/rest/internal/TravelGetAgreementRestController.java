package org.javaguru.travel.insurance.rest.internal;

import com.google.common.base.Stopwatch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import org.javaguru.travel.insurance.core.services.TravelGetAgreementService;
import org.javaguru.travel.insurance.dto.internal.DtoGetConvertor;
import org.javaguru.travel.insurance.dto.internal.TravelGetAgreementResponse;
import org.javaguru.travel.insurance.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/insurance/travel/api/internal/agreement")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelGetAgreementRestController {

    private final TravelGetAgreementRequestLogger requestLogger;
    private final TravelGetAgreementResponseLogger responseLogger;
    private final TravelCalculatePremiumRequestExecutionTimeLogger executionTimeLogger;
    private final TravelGetAgreementService service;
    private final DtoGetConvertor dtoGetConvertor;

    @GetMapping(path = "/{uuid}",
            produces = "application/json")
    public TravelGetAgreementResponse getAgreement(@PathVariable("uuid") String uuid) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TravelGetAgreementResponse response = processRequest(uuid);
        executionTimeLogger.timeRecordertoLogger(stopwatch);
        return response;
    }


    private TravelGetAgreementResponse processRequest(String uuid) {
        requestLogger.log(uuid);

        TravelGetAgreementCoreCommand coreCommand = dtoGetConvertor.buildCoreCommand(uuid);
        TravelGetAgreementCoreResult coreResult = service.getAgreement(coreCommand);
        TravelGetAgreementResponse response = dtoGetConvertor.buildResponse(coreResult);

        responseLogger.log(response);
        return response;
    }

}