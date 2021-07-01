package com.globallogic.javaacademy.functional.methodreference;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

//@SuppressWarnings("all")
public class MethodReferenceApp {

    List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

    // ================================================================
    // Parameter - argument to the static method | (arg) -> Class.staticMethod(arg)
    // ================================================================
    private void staticMethodReference() {
        Consumer<Integer> lambda = number -> String.valueOf(number);
        Consumer<Integer> methodReference = String::valueOf;

        numbers.stream()
                .map(number -> String.valueOf(number))
                .forEach(number -> System.out.println(number));
        numbers.stream()
                .map(String::valueOf)
                .forEach(number -> System.out.println(number));
    }

    // ================================================================
    // Parameter - argument  | (arg) -> instance.method(arg)
    // ================================================================
    private void parameterAsArgument() {
        Consumer<Integer> lambda = number -> System.out.println(number);
        Consumer<Integer> methodReference = System.out::println;

        numbers.forEach(number -> System.out.println(number));
        numbers.forEach(System.out::println);
    }

    // ================================================================
    // Parameter - target  | (target) -> target.method()
    // ================================================================
    private void parameterAsTarget() {
        Consumer<Integer> lambda = number -> number.doubleValue();
        Consumer<Integer> methodReference = Integer::doubleValue;

        numbers.forEach(number -> number.doubleValue());
        numbers.forEach(Integer::doubleValue);
    }


    // ================================================================
    //  Two parameters - one: target and the other: argument | (object, arg) -> object.method(arg)
    // ================================================================
    private void twoParametersTargetAndArgument() {
        BinaryOperator<String> lambda = (total, str) -> total.concat(str);
        BinaryOperator<String> methodReference = String::concat;

        numbers.stream()
                .map(Object::toString)
                .reduce(" ", (total, str) -> total.concat(str));

        numbers.stream()
                .map(Object::toString)
                .reduce(" ", String::concat);
    }

    // ================================================================
    //  Two parameters - both arguments | (arg1, arg2) -> method(arg1, arg1)
    // ================================================================
    private void twoParametersAsArgument() {
        BinaryOperator<Integer> lambda = (total, element) -> Integer.sum(total, element);
        BinaryOperator<Integer> methodReference = Integer::sum;

        numbers.stream()
                .reduce(0, Integer::sum);

        numbers.stream()
                .reduce(0, (total, element) -> Integer.sum(total, element));
    }

    // ================================================================
    //  To constructor | arg -> new Class(arg)
    // ================================================================
    private void referenceToConstructor() {
        Consumer<Integer> lambda = number -> new BigDecimal(number);
        Consumer<Integer> methodReference = BigDecimal::new;

        numbers.forEach(number -> new BigDecimal(number));
        numbers.forEach(BigDecimal::new);
    }

}
