package com.umiitkose.events.example.stream;

import com.umiitkose.events.data.model.TopMovies;

import java.util.ArrayList;
import java.util.List;

public class ImperativeSolution {
    void main() {
        List<TopMovies> top100TopMoviess = TopMovies.get100TopMoviess();

        // Imperative solution

        // Bana son 2000 yılından sonra çıkan ve 8.0 üzeri olan filmleri sıralanmış haliyle getir
        List<TopMovies> movies= new ArrayList<>();
        for (TopMovies topMovies : top100TopMoviess) {
            if (topMovies.year() > 2010 && topMovies.rating() > 8.0) {
                movies.add(topMovies);
            }
        }

        movies.sort((o1, o2) -> {
            if (o1.year() > o2.year()) {
                return 1;
            } else if (o1.year() < o2.year()) {
                return -1;
            } else {
                return 0;
            }
        });

        for (int i = movies.size() - 1; i >= 0; i--) {
            System.out.println(movies.get(i));
        }

    }
}
