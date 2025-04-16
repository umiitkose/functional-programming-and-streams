package com.umiitkose.events.example.stream;

import com.umiitkose.events.data.model.TopMovies;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Stream;

public class AverageRatingAfter2000 {
    void main() {
        List<TopMovies> top100TopMoviess = TopMovies.get100TopMoviess();

        DoubleSummaryStatistics doubleSummaryStatistics = getStream(top100TopMoviess)
                .filter(topMovies -> topMovies.year() > 2000)
                .mapToDouble(TopMovies::rating)
                .summaryStatistics();

        System.out.println(doubleSummaryStatistics);

        getStream(top100TopMoviess)
                .filter(topMovies -> topMovies.year() > 2000)
                .filter(topMovies -> topMovies.rating()>8.8)
                .findFirst().ifPresent(System.out::println);

    }

    private static Stream<TopMovies> getStream(List<TopMovies> top100TopMoviess) {
        return top100TopMoviess.stream();
    }
}
