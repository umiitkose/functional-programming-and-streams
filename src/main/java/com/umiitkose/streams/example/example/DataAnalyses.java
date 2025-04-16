package com.umiitkose.streams.example.example;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

public class DataAnalyses {
    void main() {
        List<Transaction> transactions = List.of(
                new Transaction("Food", 100, "2023-01-01"),
                new Transaction("Transport", 50, "2023-01-02"),
                new Transaction("Food", 200, "2023-01-03"),
                new Transaction("Entertainment", 150, "2023-01-04"),
                new Transaction("Food", 300, "2023-01-05")
        );

        // Group by category and sum the money
        Map<String, Map<String, Double>> categorySalesByMonth = transactions
                .stream()
                .collect(
                        groupingBy(
                                transaction -> transaction.category,
                                groupingBy(
                                        transaction -> transaction.date.substring(0, 7),
                                        summingDouble(transaction -> transaction.money)
                                )
                        )
                );
        System.out.println(categorySalesByMonth);
    }

    private record Transaction(String category, int money, String date) {
    }
}
