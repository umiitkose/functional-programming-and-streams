package com.umiitkose.events.example.stream;

import com.umiitkose.events.data.model.TopMovies;

import java.util.List;

public class DeclarativeSolution {

    void main(){

        /** SELECT * FROM movies WHERE year>2010 AND rating>8.0 ORDER BY DESC */
        List<TopMovies> top100TopMoviess = TopMovies.get100TopMoviess();
        top100TopMoviess.stream()
                .filter(topMovies -> topMovies.year() > 2010 && topMovies.rating() > 8.0)
                .sorted((o1, o2) -> Integer.compare(o2.year(), o1.year()))
                .forEach(System.out::println);
    }
}
