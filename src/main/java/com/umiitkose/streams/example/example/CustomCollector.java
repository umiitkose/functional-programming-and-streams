package com.umiitkose.streams.example.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomCollector {
    // Custom collector implementation
    public static <T> Collector<T, ?, List<T>> toList() {
        return Collector.of(
                ArrayList::new,
                List::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                }
        );
    }

    void main() {
        // Example usage
        List<String> list = Stream.of("a", "b", "c")
                .collect(toList());
        System.out.println(list); // Output: [a, b, c]


        // Example usage with a custom collector
        List<Person> personList = List.of(
                new Person("Ümit", 32, "Ankara"),
                new Person("Sinem", 28, "Ankara"),
                new Person("Furkan", 25, "İstanbul"),
                new Person("Evren", 34, "Muğla")
        );

        Map<String, Double> averageAgeByCity = personList
                .stream()
                .collect(Collectors.groupingBy(
                                Person::city,
                                Collectors.averagingInt(Person::age)
                        )
                );

        System.out.println(averageAgeByCity);

    }

    private record Person(String name, int age, String city) {
    }
}
