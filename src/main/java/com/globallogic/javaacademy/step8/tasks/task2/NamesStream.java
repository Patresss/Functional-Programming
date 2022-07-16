package com.globallogic.javaacademy.step8.tasks.task2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NamesStream {

    /**
     * You have list of names. Please find all names
     * - with letter 'a' or 'A' and
     * - the size of this name should be greater or equals 4 and
     * - convert all of the characters to upper case
     * - and return a map with name as key and number of letter as value
     *
     * Example: List ["Anna", "Rita", "Ala", "Piotr", "Patryk", "Wojtek"]
     * Result: {RITA=4, ANNA=4, PATRYK=6}
     */

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Anna", "Rita", "Ala", "Piotr", "Patryk", "Wojtek");
        System.out.println(selectNames(names));
    }

    public static Map<String, Integer> selectNames(final List<String> names) {
        // show debug mode
        return names.stream()
                .map(String::toUpperCase)
                .filter(name -> name.contains("A"))
                .filter(name -> name.length() >= 4)
                .collect(Collectors.toMap(Function.identity(), String::length));
    }

}
