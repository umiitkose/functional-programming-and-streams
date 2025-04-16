package com.umiitkose.streams.spliterator;

import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger();
        List<Article> java = Stream.generate(() -> new Article(i.getAndIncrement(), "Java"))
                .limit(35000)
                .collect(Collectors.toList());

        System.out.println(java.get(5));
        Spliterator<Article> spliterator1 = java.spliterator();
        System.out.println("Characteristics : " + spliterator1.characteristics());
        System.out.println("EstimateSize : " + spliterator1.estimateSize());
        while (spliterator1.tryAdvance(test -> test.setName(test.getName().concat(" Ümit")))) ;

        //java.stream().forEach(System.out::println);

        /**
         * It has eight different characteristics that describe its behaviour. Those can be used as hints for external tools:
         *
         * SIZED – if it’s capable of returning an exact number of elements with the estimateSize() method
         * SORTED – if it’s iterating through a sorted source
         * SUBSIZED – if we split the instance using a trySplit() method and obtain Spliterators that are SIZED as well
         * CONCURRENT – if the source can be safely modified concurrently
         * DISTINCT – if for each pair of encountered elements x, y, !x.equals(y)
         * IMMUTABLE – if elements held by the source can’t be structurally modified
         * NONNULL – if the source holds nulls or not
         * ORDERED – if iterating over an ordered sequence
         * */

        long l = System.currentTimeMillis();
        List<Integer> example = Stream.of(1, 2, 3, 4, 5,5,6,7,8,9,0,10,2,3,1,2,4,223,1,12,34,54)
                .filter(i1 -> i1 > 4)
                .map(i1 -> i1 * 2)
                .distinct()
                .limit(3)
                .sorted()
                .toList();

        long l2 = System.currentTimeMillis();
        System.out.println(example + " " + (l2 - l) + " ms");
    }
}
