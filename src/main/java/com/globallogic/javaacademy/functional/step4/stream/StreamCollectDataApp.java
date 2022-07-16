package com.globallogic.javaacademy.functional.step4.stream;

import com.globallogic.javaacademy.model.Address;
import com.globallogic.javaacademy.model.Payment;
import com.globallogic.javaacademy.model.PaymentStatus;
import com.globallogic.javaacademy.model.Person;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class StreamCollectDataApp {

    private static final List<Payment> payments = List.of(
            new Payment(new BigDecimal("20.37"), "USD", PaymentStatus.NEW),
            new Payment(new BigDecimal("21.37"), "PLN", PaymentStatus.NEW),
            new Payment(new BigDecimal("14.99"), "PLN", PaymentStatus.SUSPENDED),
            new Payment(new BigDecimal("100.37"), "PLN", PaymentStatus.SUSPENDED),
            new Payment(new BigDecimal("200.37"), "USD", PaymentStatus.CREATED)
    );

    private static final List<Person> persons = List.of(
            new Person("Tom", "Apple", 18, List.of(new Address("Kraków", "Fabrczna"))),
            new Person("Ola", "Tapic", 4, List.of(new Address("Kraków", "Fabrczna"))),
            new Person("Jan", "Żółty", 21, List.of(new Address("Wadowice", "Kremówkowa")))
    );

    @Test
    public void groupBy() {
        // ===================================================================
        // Without stream
        // ===================================================================
        final Map<PaymentStatus, List<Payment>> paymentStatusPaymentMapWithoutStream = new HashMap<>();
        for (Payment payment : payments) {
            final PaymentStatus paymentStatus = payment.getPaymentStatus();
            List<Payment> paymentsByStatus = paymentStatusPaymentMapWithoutStream.get(paymentStatus);
            if (paymentsByStatus == null) {
                paymentsByStatus = new ArrayList<>();
                paymentStatusPaymentMapWithoutStream.put(paymentStatus, paymentsByStatus);
            }
            paymentsByStatus.add(payment);
        }

        System.out.println("Result without stream:");
        paymentStatusPaymentMapWithoutStream.entrySet()
                .forEach(System.out::println);

        // ===================================================================
        // With stream
        // ===================================================================
        final Map<PaymentStatus, List<Payment>> paymentStatusPaymentMap = payments.stream()
                .collect(Collectors.groupingBy(Payment::getPaymentStatus));

        System.out.println("Result:");
        paymentStatusPaymentMap.entrySet()
                .forEach(System.out::println);
    }


    @Test
    public void max() {
        final Optional<Person> result = persons.stream()
                .max(Comparator.comparing(Person::getAge));
//        final Optional<Person> result = persons.stream()
//                .collect(Collectors.maxBy(Comparator.comparing(Person::getAge)));
//
        System.out.println("Result: \n");
        System.out.println(result);
    }

    @Test
    public void averaging() {
        final double result = persons.stream()
                .collect(Collectors.averagingInt(Person::getAge));

        System.out.println("Result: \n");
        System.out.println(result);
    }

    @Test
    public void joining() {
        // ===================================================================
        // Without stream
        // ===================================================================
        final StringBuilder stringBuilder = new StringBuilder();
        final String delimiter = ", ";
        for (int i = 0; i < persons.size(); i++) {
            stringBuilder.append(persons.get(i).getName());
            if (i <  persons.size() - 1) {
                stringBuilder.append(delimiter);
            }
        }
        System.out.println("Result without stream:");
        System.out.println(stringBuilder);


        // ===================================================================
        // With stream
        // ===================================================================
        final String result = persons.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));

        System.out.println("Result:");
        System.out.println(result);
    }

    @Test
    public void partitioningBy() {
        final Map<Boolean, List<Person>> result = persons.stream()
                .collect(Collectors.partitioningBy(person -> person.getAge() >= 18));

        System.out.println("Result: \n");
        System.out.println(result);
    }

}
