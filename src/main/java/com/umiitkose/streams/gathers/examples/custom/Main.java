package com.umiitkose.streams.gathers.examples.custom;

import java.util.List;

public class Main {
    private List<Log> logWrappers = List.of(
            new Log(Log.Level.ERROR, "Process ID: 191, event details ..."),
            new Log(Log.Level.INFO, "Process ID: 216, event details ..."),
            new Log(Log.Level.DEBUG, "Process ID: 279, event details ..."),
            new Log(Log.Level.ERROR, "Process ID: 312, event details ..."),
            new Log(Log.Level.WARNING, "Process ID: 340, event details ..."),
            new Log(Log.Level.ERROR, "Process ID: 367, event details ..."),
            new Log(Log.Level.ERROR, "Process ID: 389, event details ..."),
            new Log(Log.Level.INFO, "Process ID: 401, event details ..."),
            new Log(Log.Level.ERROR, "Process ID: 416, event details ..."),
            new Log(Log.Level.ERROR, "Process ID: 417, event details ..."),
            new Log(Log.Level.ERROR, "Process ID: 418, event details ..."),
            new Log(Log.Level.WARNING, "Process ID: 432, event details ..."),
            new Log(Log.Level.ERROR, "Process ID: 444, event details ..."),
            new Log(Log.Level.ERROR, "Process ID: 445, event details ..."),
            new Log(Log.Level.ERROR, "Process ID: 446, event details ..."),
            new Log(Log.Level.ERROR, "Process ID: 447, event details ...")
    );

    void main(){

        logWrappers.stream().gather(CustomGatherer.of(3))
                .toList().forEach(System.out::println);
    }
}
