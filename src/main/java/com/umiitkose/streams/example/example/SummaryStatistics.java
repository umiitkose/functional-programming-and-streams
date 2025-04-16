package com.umiitkose.streams.example.example;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Stream;

public class SummaryStatistics {
    void main(){
        DoubleSummaryStatistics doubleSummaryStatistics = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(i -> i > 5)
                .mapToDouble(Integer::doubleValue)
                .summaryStatistics();

        System.out.println(doubleSummaryStatistics);
    }
}
