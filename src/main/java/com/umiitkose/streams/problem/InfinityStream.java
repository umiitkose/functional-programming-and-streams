package com.umiitkose.streams.problem;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class InfinityStream {
    public static void main(String[] args) {

        //generate UNORDERED karakteristike sahip ve aşağıdaki kod 1000 tane olacağını garanti etmez.
        // Her çalıştırmada farklı yanıt alınır.
        Stream.generate(new AtomicInteger()::incrementAndGet)
                .parallel()
                .limit(1_000L)
                .mapToInt(Integer::valueOf)
                .max()
                .ifPresent(System.out::println);
    }
}
