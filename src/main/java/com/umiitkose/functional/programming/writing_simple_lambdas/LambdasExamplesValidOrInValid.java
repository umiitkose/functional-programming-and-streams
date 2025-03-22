package com.umiitkose.functional.programming.writing_simple_lambdas;

import java.nio.file.DirectoryStream;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Gatherer;

public class LambdasExamplesValidOrInValid {
    public static void main(String[] args) {
        BiFunction<String, String, Boolean> fish = (String x, String y) -> x.startsWith("fish");
        Function<String, Boolean> fishFunction = (String x) -> x.startsWith("fish");

        DirectoryStream.Filter<String> camel = (String x) -> {
            return x.startsWith("camel");
        };
        DirectoryStream.Filter<String> giraffe = (String x) -> {
            return x.startsWith("giraffe");
        };
        DirectoryStream.Filter<String> eagle = (String x) -> x.endsWith("eagle");

    }
}
