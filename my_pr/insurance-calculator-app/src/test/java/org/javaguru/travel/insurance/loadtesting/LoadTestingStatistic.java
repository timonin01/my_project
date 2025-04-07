package org.javaguru.travel.insurance.loadtesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class LoadTestingStatistic {

    private List<Long> executionTimes = new ArrayList<>();

    public void addTime(Long time){executionTimes.add(time);}

    public synchronized Long calculateSum(){
        return executionTimes.stream()
                .reduce((a,b)->a+b)
                .get();
    }

    public synchronized Double calculateAverage(){
        OptionalDouble optional =  executionTimes.stream()
                .mapToDouble(a->a)
                .average();
        return optional.isPresent() ? optional.getAsDouble() : 0;
    }

    public synchronized Long findMinTime(){
        return executionTimes.stream()
                .min(Long::compareTo)
                .orElse(0L);
    }

    public synchronized Long findMaxTime(){
        return executionTimes.stream()
                .max(Long::compareTo)
                .orElse(0L);
    }

}
