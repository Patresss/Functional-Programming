package com.globallogic.javaacademy.functional.step4.stream;

import com.globallogic.javaacademy.model.Address;
import com.globallogic.javaacademy.model.Payment;
import com.globallogic.javaacademy.model.PaymentStatus;
import com.globallogic.javaacademy.model.Person;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class StreamApp {

    private static final List<Payment> payments = List.of(
            new Payment(new BigDecimal("20.37"), "USD", PaymentStatus.NEW),
            new Payment(new BigDecimal("21.37"), "PLN", PaymentStatus.NEW),
            new Payment(new BigDecimal("14.99"), "PLN", PaymentStatus.SUSPENDED),
            new Payment(new BigDecimal("100.37"), "PLN", PaymentStatus.SUSPENDED),
            new Payment(new BigDecimal("200.37"), "USD", PaymentStatus.CREATED),
            new Payment(new BigDecimal("121.37"), "PLN", PaymentStatus.NEW)
    );


    @Test
    public void filter() {
        // ===================================================================
        // Without stream
        // ===================================================================
        final List<Payment> resultWithoutStream = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.getPaymentStatus() == PaymentStatus.NEW) {
                resultWithoutStream.add(payment);
            }
        }
        System.out.println("Result without stream:");
        resultWithoutStream.forEach(System.out::println);

        // ===================================================================
        // With stream
        // ===================================================================
        final List<Payment> result = payments.stream()
                .filter(payment -> payment.getPaymentStatus() == PaymentStatus.NEW)
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void map() {
        // ===================================================================
        // Without stream
        // ===================================================================
        final List<BigDecimal> resultWithoutStream = new ArrayList<>();
        for (Payment payment : payments) {
            final BigDecimal amount = payment.getAmount();
            resultWithoutStream.add(amount);
        }
        System.out.println("Result without stream:");
        resultWithoutStream.forEach(System.out::println);

        // ===================================================================
        // With stream
        // ===================================================================
        final List<BigDecimal> result = payments.stream()
                .map(Payment::getAmount)
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void limit() {
        // ===================================================================
        // Without stream
        // ===================================================================
        final List<Payment> resultWithoutStream = new ArrayList<>();
        for (Payment payment : payments) {
            resultWithoutStream.add(payment);
            if (resultWithoutStream.size() >= 3) {
                break;
            }
        }
        System.out.println("Result without stream:");
        resultWithoutStream.forEach(System.out::println);

        // ===================================================================
        // With stream
        // ===================================================================
        final List<Payment> result = payments.stream()
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void skip() {
        // ===================================================================
        // Without stream
        // ===================================================================
        final List<Payment> resultWithoutStream = new ArrayList<>();
        int currentIteration = 0;
        for (Payment payment : payments) {
            if (currentIteration++ >= 2) {
                resultWithoutStream.add(payment);
            }
        }
        System.out.println("Result without stream:");
        resultWithoutStream.forEach(System.out::println);

        // ===================================================================
        // With stream
        // ===================================================================
        final List<Payment> result = payments.stream()
                .skip(2)
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void collect() {
        // ===================================================================
        // Without stream
        // ===================================================================
        final Map<Payment, String> resultWithoutStream = new HashMap<>();
        for (Payment payment : payments) {
            resultWithoutStream.put(payment, payment.getAmount() + " " + payment.getCurrency());
        }
        System.out.println("Result without stream:");
        resultWithoutStream.forEach((payment, amount) -> System.out.println(payment + " | " + amount));

        // ===================================================================
        // With stream
        // ===================================================================
        final Map<Payment, String> result = payments.stream()
                .collect(Collectors.toMap(payment -> payment, payment -> payment.getAmount() + " " + payment.getCurrency()));
//                .collect(Collectors.toSet());
//                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach((payment, amount) -> System.out.println(payment + " | " + amount));
    }

    @Test
    public void reduce() {
        // ===================================================================
        // Without stream
        // ===================================================================
        BigDecimal resultWithoutStream = BigDecimal.ZERO;
        for (Payment payment : payments) {
            resultWithoutStream = resultWithoutStream.add(payment.getAmount());
        }
        System.out.println("Result without stream:");
        System.out.println(resultWithoutStream);


        // ===================================================================
        // With stream
        // ===================================================================
        final BigDecimal result = payments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Result:");
        System.out.println(result);
    }

    @Test
    public void allMatch() {
        // ===================================================================
        // Without stream
        // ===================================================================
        boolean resultWithoutStream = true;
        for (Payment payment : payments) {
            if (payment.getPaymentStatus() == PaymentStatus.NEW) {
                resultWithoutStream = false;
                break;
            }
        }
        System.out.println("Result without stream:");
        System.out.println(resultWithoutStream);

        // ===================================================================
        // With stream
        // ===================================================================
        final boolean result = payments.stream()
                .allMatch(payment -> payment.getPaymentStatus() == PaymentStatus.NEW);

        System.out.println("Result:");
        System.out.println(result);
    }

    @Test
    public void anyMatch() {
        // ===================================================================
        // Without stream
        // ===================================================================
        boolean resultWithoutStream = false;
        for (Payment payment : payments) {
            if (payment.getPaymentStatus() == PaymentStatus.NEW) {
                resultWithoutStream = true;
                break;
            }
        }
        System.out.println("Result without stream:");
        System.out.println(resultWithoutStream);

        // ===================================================================
        // With stream
        // ===================================================================
        final boolean result = payments.stream()
                .anyMatch(payment -> payment.getPaymentStatus() == PaymentStatus.NEW);

        System.out.println("Result:");
        System.out.println(result);
    }

    @Test
    public void noneMatch() {
        // ===================================================================
        // Without stream
        // ===================================================================
        boolean resultWithoutStream = true;
        for (Payment payment : payments) {
            if (payment.getPaymentStatus() != PaymentStatus.NEW) {
                resultWithoutStream = false;
                break;
            }
        }
        System.out.println("Result without stream:");
        System.out.println(resultWithoutStream);

        // ===================================================================
        // With stream
        // ===================================================================
        final boolean result = payments.stream()
                .noneMatch(payment -> payment.getPaymentStatus() == PaymentStatus.NEW);
//                .allMatch(payment -> payment.getPaymentStatus() != PaymentStatus.NEW);

        System.out.println("Result:");
        System.out.println(result);
    }

    @Test
    public void peek() {
        // ===================================================================
        // Without stream
        // ===================================================================
        final List<Payment> resultWithoutStream = new ArrayList<>();
        for (Payment payment : payments) {
            System.out.println(payment);
            if (payment.getPaymentStatus() == PaymentStatus.NEW) {
                resultWithoutStream.add(payment);
            }
        }
        System.out.println("Result without stream:");
        resultWithoutStream.forEach(System.out::println);

        // ===================================================================
        // With stream
        // ===================================================================
        final List<Payment> result = payments.stream()
                .peek(System.out::println)
                .filter(payment -> payment.getPaymentStatus() == PaymentStatus.NEW)
                .collect(Collectors.toList());

        System.out.println("Result:");
        result.forEach(System.out::println);
    }

    @Test
    public void findFirst() {
        // ===================================================================
        // Without stream
        // ===================================================================
        Payment resultWithoutStream = null;
        for (Payment payment : payments) {
            System.out.println(payment);
            if (payment.getPaymentStatus() == PaymentStatus.SUSPENDED) {
                resultWithoutStream = payment;
                break;
            }
        }
        System.out.println("Result without stream:");
        System.out.println(resultWithoutStream);
        System.out.println();

        // ===================================================================
        // With stream
        // ===================================================================
        final Payment result = payments.stream()
                .peek(System.out::println)
                .filter(payment -> payment.getPaymentStatus() == PaymentStatus.SUSPENDED)
                .findFirst()
                .orElse(null);

        System.out.println("Result:");
        System.out.println(result);
    }

    @Test
    public void distinct() {
        // ===================================================================
        // Without stream
        // ===================================================================
        final List<String> resultWithoutStream = new ArrayList<>();
        for (Payment payment : payments) {
            final String currency = payment.getCurrency();
            if (!resultWithoutStream.contains(currency)) {
                resultWithoutStream.add(currency);
            }
        }
        System.out.println("Result without stream:");
        resultWithoutStream.forEach(System.out::println);
        System.out.println();

        // ===================================================================
        // With stream
        // ===================================================================
        final List<String> result = payments.stream()
                .map(Payment::getCurrency)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Result:");
        result.forEach(System.out::println);
    }

    @Test
    public void takeWhile() {
        // ===================================================================
        // Without stream
        // ===================================================================
        final List<Payment> resultWithoutStream = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.getPaymentStatus() == PaymentStatus.NEW) {
                resultWithoutStream.add(payment);
            } else {
                break;
            }
        }
        System.out.println("Result without stream:");
        resultWithoutStream.forEach(System.out::println);
        System.out.println();

        // ===================================================================
        // With stream
        // ===================================================================
        final List<Payment> result = payments.stream()
                .takeWhile(payment -> payment.getPaymentStatus() == PaymentStatus.NEW)
                .collect(Collectors.toList());

        System.out.println("Result: \n");
        result.forEach(System.out::println);
    }

    @Test
    public void dropWhile() {
        // ===================================================================
        // Without stream
        // ===================================================================
        final List<Payment> resultWithoutStream = new ArrayList<>();
        boolean shouldDrop = true;
        for (Payment payment : payments) {
            shouldDrop = shouldDrop && payment.getPaymentStatus() != PaymentStatus.SUSPENDED;
            if (shouldDrop) {
                continue;
            }
            resultWithoutStream.add(payment);
        }
        System.out.println("Result without stream:");
        resultWithoutStream.forEach(System.out::println);
        System.out.println();

        // ===================================================================
        // With stream
        // ===================================================================
        final List<Payment> result = payments.stream()
                .dropWhile(payment -> payment.getPaymentStatus() != PaymentStatus.SUSPENDED)
                .collect(Collectors.toList());

        System.out.println("Result:");
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
                .map(this::sendNotification)
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

        // ===================================================================
        // Without stream
        // ===================================================================
        final List<Address> resultWithoutStream = new ArrayList<>();
        for (Person person : persons) {
            for (Address address : person.getAddresses()) {
                resultWithoutStream.add(address);
            }
        }
        System.out.println("Result without stream:");
        System.out.println("Result: \n" + resultWithoutStream);
        System.out.println();

        // ===================================================================
        // With stream
        // ===================================================================
        final List<Address> result = persons.stream()
                .map(Person::getAddresses)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.println("Result: \n" + result);
    }

}
