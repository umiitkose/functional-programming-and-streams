package com.umiitkose.streams.gathers.examples.Example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Gatherers;

record CustomerOrder(String customerId, LocalDateTime orderTime, double amount) {
}

public class Main {
    public static void main(String[] args) {
        List<CustomerOrder> orders = List.of(
                new CustomerOrder("C1", LocalDateTime.parse("2023-12-01T10:30:00"), 125.50),
                new CustomerOrder("C1", LocalDateTime.parse("2023-12-10T14:20:00"), 75.00),
                new CustomerOrder("C1", LocalDateTime.parse("2024-02-05T09:45:00"), 200.00),
                new CustomerOrder("C2", LocalDateTime.parse("2023-12-03T16:10:00"), 90.75),
                new CustomerOrder("C2", LocalDateTime.parse("2023-12-04T11:30:00"), 150.25)
        );

        // Müşteri bazında sıralayıp, 30 günden fazla alışveriş yapılmayan aralıkları bul
        Map<String, List<Duration>> longGapsByCustomer = orders.stream()
                .sorted(Comparator.comparing(CustomerOrder::customerId)
                        .thenComparing(CustomerOrder::orderTime))
                .gather(Gatherers.windowSliding(2))
                .filter(window -> window.size() == 2 &&
                        window.getFirst().customerId().equals(window.get(1).customerId()))
                .map(window -> Map.entry(
                        window.getFirst().customerId(),
                        Duration.between(window.getFirst().orderTime(), window.get(1).orderTime())
                ))
                .filter(entry -> entry.getValue().toDays() > 30)
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));

        // Sonuçları yazdır
        longGapsByCustomer.forEach((customerId, gaps) -> {
            System.out.println("Müşteri " + customerId + " için uzun alışveriş araları:");
            gaps.forEach(gap -> System.out.println("  " + gap.toDays() + " gün"));
        });
    }
}

