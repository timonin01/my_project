package org.javaguru.travel.insurance.loadtesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class LoadTestingStatistic {

    private List<Long> executionTimes = new ArrayList<>();

    public void addTime(Long time){executionTimes.add(time);}

    public Double calculateAverage(){
        OptionalDouble optional =  executionTimes.stream()
                .mapToDouble(a->a)
                .average();
        return optional.isPresent() ? optional.getAsDouble() : 0;
    }

    public Long findMinTime(){
        return executionTimes.stream()
                .min(Long::compareTo)
                .orElse(0L);
    }

    public Long findMaxTime(){
        return executionTimes.stream()
                .max(Long::compareTo)
                .orElse(0L);
    }

}
