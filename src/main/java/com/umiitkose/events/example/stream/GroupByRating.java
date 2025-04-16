package com.umiitkose.events.example.stream;

import com.umiitkose.events.data.model.TopMovies;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByRating {
    void main(){
        List<TopMovies> topMoviesList = TopMovies.get100TopMoviess();

        Map<Double, List<String>> collect = topMoviesList.stream()
                .collect(
                        Collectors.groupingBy(
                                TopMovies::rating,
                                () -> new java.util.TreeMap<>(Comparator.reverseOrder()),
                                Collectors.mapping(TopMovies::title, Collectors.toList())
                        )
                );

        System.out.println(collect);
    }
}
