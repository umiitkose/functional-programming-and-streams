package com.umiitkose.streams.introduction;

import java.util.ArrayList;
import java.util.List;

public class BookProcessing {
    void main() {
        List<Book> books = new ArrayList<>() {
            {
                add(new Book("Dune", 1965, Genre.SCIENCE_FICTION));
                add(new Book("The Shining", 1977, Genre.HORROR));
                add(new Book("The Hobbit", 1937, Genre.FANTASY));
                add(new Book("Foundation", 1951, Genre.SCIENCE_FICTION));
                add(new Book("Neuromancer", 1984, Genre.SCIENCE_FICTION));
            }
        };

        books.sort((o1, o2) -> Integer.compare(o2.year(), o1.year()));

        System.out.println("Books sorted by year:");
        System.out.println(books);

        List<Book> filterBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.year() > 1990) {
                continue;
            }
            if (book.genre() != Genre.SCIENCE_FICTION) {
                continue;
            }

            if(filterBooks.size()>=2){
                break;
            }

            filterBooks.add(book);

        }

        System.out.println("Books filtered by year and genre:");
        System.out.println(filterBooks);
    }
}
