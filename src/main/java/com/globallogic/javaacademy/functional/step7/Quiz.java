package com.globallogic.javaacademy.functional.step7;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class Quiz {

    // =====================================================================================================
    @Test
    public void question1() {
        // What this function will write out?
        final List<String> names = List.of("Jan", "Paweł", "Karol");
        final List<String> processedNames = names.stream()
                .filter(name -> filterNameQuestion1(name))
                .map(name -> mapNameQuestion1(name))
                .collect(Collectors.toList());
        /*
            A)                          B)
                FILTER - Jan                FILTER - Jan
                FILTER - Paweł              FILTER - Paweł
                FILTER - Karol              MAP - Paweł
                MAP - Paweł                 FILTER - Karol
                MAP - Karol                 MAP - Karol

         */
    }

    private static boolean filterNameQuestion1(String name) {
        System.out.println("FILTER - " + name);
        return name.length() > 4;
    }

    private static String mapNameQuestion1(String name) {
        System.out.println("MAP - " + name);
        return name.toUpperCase();
    }

    // =====================================================================================================
    @Test
    public void question2() {
        // What this function will write out?
        final List<String> names = List.of("Jan", "Paweł", "Karol");
        final Long count = names.stream()
                .map(name -> mapNameQuestion2(name))
                .count();
        /*
            A)                          B)
                MAP - Jan                   <nothing>
                MAP - Paweł
                MAP - Karol
         */
    }

    private static String mapNameQuestion2(String name) {
        System.out.println("MAP - " + name);
        return name.toUpperCase();
    }

    // =====================================================================================================
    @Test
    public void question3() {
        // What this function will write out?
        final List<String> names = List.of("Jan", "Paweł", "Karol");
        names.stream()
                .map(name -> mapNameQuestion3(name));
        /*
            A)                          B)                  C)
                MAP - Jan                   <nothing>           Throw exception
                MAP - Paweł
                MAP - Karol
         */
    }

    private static String mapNameQuestion3(String name) {
        System.out.println("MAP - " + name);
        return name.toUpperCase();
    }

    // =====================================================================================================
    @Test
    public void question4() {
        // What this function will write out?
        final String name = null;
        final Optional<String> optionalName = Optional.of(name);
        System.out.println(optionalName);
        /*
            A)                          B)
                Optional.empty              java.lang.NullPointerException
         */
    }

    // =====================================================================================================
    @Test
    public void question5() {
        // What this function will write out?
        final String name = Optional.ofNullable(calculateNameQuestion5())
                .orElse(calculateDefaultQuestion5());
        System.out.println(name);
        /*
            A)                                  B)
                Calculating name...                 Calculating default name...
                Calculating default name...         Patryk
                Patryk
         */
    }

    private String calculateNameQuestion5() {
        System.out.println("Calculating name...");
        return null;
    }

    private String calculateDefaultQuestion5() {
        System.out.println("Calculating default name...");
        return "Patryk";
    }

    // =====================================================================================================
    @Test
    public void question6() {
        // What this function will write out?
        final String name = Optional.ofNullable(calculateNameQuestion6())
                .orElse(calculateDefaultQuestion6());
        System.out.println(name);
        /*
            A)                                  B)                                  C)                                  D)
                Calculating name...                 Calculating default name...         Calculating name...                 Calculating name...
                Calculating default name...         Patryk                              Calculating default name...         Tom
                Patryk                                                                  Tom
         */
    }

    private String calculateNameQuestion6() {
        System.out.println("Calculating name...");
        return "Tom";
    }

    private String calculateDefaultQuestion6() {
        System.out.println("Calculating default name...");
        return "Patryk";
    }

    // =====================================================================================================
    @Test
    public void question7() {
        // What this function will write out?
        final String name = Optional.ofNullable(calculateNameQuestion7())
                .orElseGet(() -> calculateDefaultQuestion7());
        System.out.println(name);
        /*
            A)                                  B)                                  C)                                  D)
                Calculating name...                 Calculating default name...         Calculating name...                 Calculating name...
                Calculating default name...         Patryk                              Calculating default name...         Tom
                Patryk                                                                  Tom
         */
    }

    private String calculateNameQuestion7() {
        System.out.println("Calculating name...");
        return "Tom";
    }

    private String calculateDefaultQuestion7() {
        System.out.println("Calculating default name...");
        return "Patryk";
    }

}
