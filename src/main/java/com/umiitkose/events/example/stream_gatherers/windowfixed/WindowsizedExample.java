package com.umiitkose.events.example.stream_gatherers.windowfixed;

import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class WindowsizedExample {
    void main(){
        Stream.of(1,2,3,4,5,6,7)
                .gather(Gatherers.windowFixed(3))
                .forEach(System.out::print);
    }
}
