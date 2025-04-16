package com.umiitkose.events.example.stream;

import com.umiitkose.events.data.model.TopMovies;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class YearGroup {
    void main(){
        List<TopMovies>
                 topMoviesList = TopMovies.get100TopMoviess();

        Map<Integer, List<String>> collect = topMoviesList.stream()
                .collect(Collectors.groupingBy(
                TopMovies::year,
                Collectors.mapping(TopMovies::title, Collectors.toList())
        ));

        System.out.println(collect);

        // Bana en çok hangi yılda kaç tane film var, bunu sırala ve en çok olan 3 taneyi getir
        Map<Integer, Long> collect1 = topMoviesList.stream()
                .collect(Collectors.groupingBy(
                        TopMovies::year,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted((o1, o2) -> Long.compare(o2.getValue(), o1.getValue()))
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("collect1 = " + collect1);



    }
}
