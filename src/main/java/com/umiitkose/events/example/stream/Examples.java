package com.umiitkose.events.example.stream;

import com.umiitkose.events.data.model.TopMovies;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Examples {
    void main() {
        List<TopMovies> topMoviesList = TopMovies.get100TopMoviess();

        // 1. Filter movies with rating greater than 8.0
        List<TopMovies> filteredMovies = topMoviesList.stream()
                .filter(movie -> movie.rating() > 8.0)
                .toList();

        // 2. distinct

        List<TopMovies> distinctMovies = topMoviesList.stream()
                .distinct()
                .toList();

        // 3. sorted
        List<TopMovies> sortedMovies = topMoviesList.stream()
                .sorted(Comparator.comparingDouble(TopMovies::rating))
                .toList();

        // 4. sorted with reverse order
        List<TopMovies> sortedMoviesReverse = topMoviesList.stream()
                .sorted(Comparator.comparingDouble(TopMovies::rating).reversed())
                .toList();

        // 5. takewhile
        List<TopMovies> takeWhileMovies = topMoviesList.stream()
                .takeWhile(movie -> movie.rating() > 8.0)
                .toList();

        // 6. dropwhile
        List<TopMovies> dropWhileMovies = topMoviesList.stream()
                .dropWhile(movie -> movie.rating() > 8.0)
                .toList();

        // 7. limit
        List<TopMovies> limitedMovies = topMoviesList.stream()
                .limit(10)
                .toList();

        // 8. skip
        List<TopMovies> skippedMovies = topMoviesList.stream()
                .skip(3)
                .toList();

        //9. mapping
        List<String> movieTitles = topMoviesList.stream()
                .map(TopMovies::title)
                .toList();

        //10 map
        List<Integer> moviesLength = topMoviesList.stream()
                .map(TopMovies::title)
                .map(String::length)
                .toList();

        //11 flatMap
        List<String> movieGenres = Stream.of(Stream.of("Action", "Drama"), Stream.of("Comedy", "Horror"))
                .flatMap(genre -> genre)
                .toList();
        System.out.println(movieGenres);

        //peek
        List<TopMovies> peekMovies = topMoviesList.stream()
                .peek(movie -> System.out.println("Movie: " + movie.title()))
                .toList();

        //12. count
        long count = topMoviesList.stream()
                .filter(movie -> movie.rating() > 8.0)
                .count();

        //13.min
        TopMovies minMovie = topMoviesList.stream()
                .min(Comparator.comparingDouble(TopMovies::rating))
                .orElse(null);
        //14 Max
        TopMovies maxMovie = topMoviesList.stream()
                .max(Comparator.comparingDouble(TopMovies::rating))
                .orElse(null);
        //15. forEach
        topMoviesList.stream()
                .forEach(movie -> System.out.println("Movie: " + movie.title()));

        //
        topMoviesList.stream().reduce((movie1, movie2) -> {
            if (movie1.rating() > movie2.rating()) {
                return movie1;
            } else {
                return movie2;
            }
        }).ifPresent(movie -> System.out.println("Highest rated movie: " + movie.title()));
      //anyMatch
        boolean b = topMoviesList.stream()
                .anyMatch(movie -> movie.rating() > 8.0);
        System.out.println("Any movie with rating > 8.0: " + b);


    }
}
