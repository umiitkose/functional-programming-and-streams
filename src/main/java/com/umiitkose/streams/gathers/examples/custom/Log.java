package com.umiitkose.streams.gathers.examples.custom;

record Log(Level level, String details) {

    enum Level {
        INFO,
        DEBUG,
        WARNING,
        ERROR
    }

}