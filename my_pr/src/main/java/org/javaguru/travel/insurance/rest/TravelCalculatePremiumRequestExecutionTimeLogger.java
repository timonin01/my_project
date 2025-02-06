package org.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class TravelCalculatePremiumRequestExecutionTimeLogger {

    private final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestExecutionTimeLogger.class);

    public void timeRecordertoLogger(Stopwatch stopwatch){
        stopwatch.stop();
        long time = stopwatch.elapsed().toMillis();
        logger.info("Request processing time (ms): " + time);
    }

}
