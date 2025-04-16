package com.umiitkose.streams.example.source;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SourceStream {
    void main(){
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        var streamList = list.stream();

        String content = "Hello, World!";
        var chars = content.chars();
        var lines = content.lines();

        Pattern pattern = Pattern.compile("\\s+");
        var stringStream = pattern.splitAsStream(content);

        var stream = Stream.of("one", "two", "three");

        var stream1 = Stream.iterate(0, n -> n + 1);

        var stream2 = Stream.generate(() -> "Hello");

        IntStream range = IntStream.range(1, 10);// 1-10 arası


        // infinite Stream

        Stream.iterate(0, n -> n + 1)
                .limit(10) //limit olmasaydı sonsuz döngü olurdu.
                .forEach(System.out::println);



    }
}
