package com.umiitkose.streams.gathers;

import java.util.List;
import java.util.stream.Gatherers;

public class Main {

    void main() {
        List<List<String>> lists = gatherIntoWindows(List.of("A", "B", "C", "D", "E", "F"));
        System.out.println(lists.toString());
    }

    public List<List<String>> gatherIntoWindows(List<String> countries) {
        return countries
                .stream()
                .gather(Gatherers.windowSliding(3))
                .toList();
    }


    /**
     * fold
     * mapConcurrent
     * scan
     * windowFixed
     * windowSliding
     * */
}
