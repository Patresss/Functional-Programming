package com.globallogic.javaacademy.functional.stream;

import com.globallogic.javaacademy.model.Address;
import com.globallogic.javaacademy.model.Payment;
import com.globallogic.javaacademy.model.PaymentStatus;
import com.globallogic.javaacademy.model.Person;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamApp {

    private static final List<Payment> payments = List.of(
            new Payment(new BigDecimal("20.37"), "USD", PaymentStatus.NEW),
            new Payment(new BigDecimal("21.37"), "PLN", PaymentStatus.NEW),
            new Payment(new BigDecimal("14.99"), "PLN", PaymentStatus.SUSPENDED),
            new Payment(new BigDecimal("100.37"), "PLN", PaymentStatus.SUSPENDED),
            new Payment(new BigDecimal("200.37"), "USD", PaymentStatus.CREATED)
    );



    @Test
    public void filter() {
        final List<Payment> result = payments.stream()
                .filter(payment -> payment.getPaymentStatus() == PaymentStatus.NEW)
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void map() {
        final List<BigDecimal> result = payments.stream()
                .map(Payment::getAmount)
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void limit() {
        final List<Payment> result = payments.stream()
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void skip() {
        final List<Payment> result = payments.stream()
                .skip(2)
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void collect() {
        final Map<Payment, String> result = payments.stream()
                .collect(Collectors.toMap(payment -> payment, payment -> payment.getAmount() + " " + payment.getCurrency()));
//                .collect(Collectors.toSet());
//                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach((payment, amount) -> System.out.println(payment + " | " + amount));
    }

    @Test
    public void reduce() {
        final BigDecimal result = payments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Result: \n");
        System.out.println(result);
    }

    @Test
    public void allMatch() {
        final boolean result = payments.stream()
                .allMatch(payment -> payment.getPaymentStatus() == PaymentStatus.NEW);

        System.out.println("Result: \n");
        System.out.println(result);
    }

    @Test
    public void anyMatch() {
        final boolean result = payments.stream()
                .anyMatch(payment -> payment.getPaymentStatus() == PaymentStatus.NEW);

        System.out.println("Result: \n");
        System.out.println(result);
    }

    @Test
    public void noneMatch() {
        final boolean result = payments.stream()
                .noneMatch(payment -> payment.getPaymentStatus() == PaymentStatus.NEW);
//                .allMatch(payment -> payment.getPaymentStatus() != PaymentStatus.NEW);

        System.out.println("Result: \n");
        System.out.println(result);
    }

    @Test
    public void peek() {
        final List<Payment> result = payments.stream()
                .peek(System.out::println)
                .filter(payment -> payment.getPaymentStatus() == PaymentStatus.NEW)
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void findFirst() {
        final Payment result = payments.stream()
                .peek(System.out::println)
                .filter(payment -> payment.getPaymentStatus() == PaymentStatus.SUSPENDED)
                .findFirst()
                .orElse(null);

        System.out.println("Result: \n");
        System.out.println(result);
    }

    @Test
    public void distinct() {
        final List<String> result = payments.stream()
                .map(Payment::getCurrency)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void takeWhile() {
        final List<Payment> result = payments.stream()
                .takeWhile(payment -> payment.getPaymentStatus() == PaymentStatus.NEW)
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void dropWhile() {
        final List<Payment> result = payments.stream()
                .dropWhile(payment -> payment.getPaymentStatus() != PaymentStatus.SUSPENDED)
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void sort() {
        final List<Payment> result = payments.stream()
                .sorted(Comparator.comparing(Payment::getCurrency))
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void count() {
        final long result = payments.stream()
                .map(payment -> sendNotification(payment))
                .count();

        System.out.println("Result: \n" + result);
    }

    private boolean sendNotification(Payment payment) {
        System.out.println("Send " + payment);
        return true;
    }


    @Test
    public void flatMap() {
        final List<Person> persons = List.of(
                new Person("Tom", "Apple", 18, List.of(new Address("Kraków", "Fabrczna"))),
                new Person("Ola", "Tapic", 4, List.of(new Address("Kraków", "Fabrczna"))),
                new Person("Jan", "Żółty", 21, List.of(new Address("Wadowice", "Kremówkowa")))
        );

        final List<Address> result = persons.stream()
                .map(Person::getAddresses)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.println("Result: \n" + result);
    }

}
