package com.umiitkose.events.data;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<List<Integer>> generateIntegerLists() {
        List<Integer> list1Million = new ArrayList<>(1_000_000);
        List<Integer> list10Thousand = new ArrayList<>(10_000);
        List<Integer> list1Thousand = new ArrayList<>(1_000);

        // Populate the lists
        for (int i = 1; i <= 1_000_000; i++) {
            list1Million.add(i);
            if (i <= 10_000) {
                list10Thousand.add(i);
            }
            if (i <= 1_000) {
                list1Thousand.add(i);
            }
        }

        // Return the lists as a collection
        List<List<Integer>> result = new ArrayList<>();
        result.add(list1Million);
        result.add(list10Thousand);
        result.add(list1Thousand);

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = generateIntegerLists();

        System.out.println("1 Million List Size: " + lists.get(0).size());
        System.out.println("10 Thousand List Size: " + lists.get(1).size());
        System.out.println("1 Thousand List Size: " + lists.get(2).size());
    }
}
