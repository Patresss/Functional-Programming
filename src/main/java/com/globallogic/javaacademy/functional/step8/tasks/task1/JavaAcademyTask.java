package com.globallogic.javaacademy.functional.step8.tasks.task1;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaAcademyTask {

    /**
     * Code Part: Please create a simple program that consumes one string as an application argument.
     * Program should count the number of each letter in the string and present the results to the console.
     * Sample output:
     * <p>
     * Counted letters:
     * m = 2
     * y = 2
     * a = 5
     * p = 2
     * j = 1
     * v = 1
     * c = 1
     * d = 1
     * <p>
     * So the argument is “JavaAcademy”.
     */

    public static void main(String[] args) {
        final String output = "JavaAcademy";

        final Map<Character, Long> frequency = output.toLowerCase()
                .chars()
                .mapToObj(it -> (char) it)
                .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
        System.out.println(frequency);

    }

}
