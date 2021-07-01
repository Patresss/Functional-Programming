package com.globallogic.javaacademy.functional.lambda;

import com.globallogic.javaacademy.model.Payment;
import com.globallogic.javaacademy.model.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("all")
public class LambdaApp {

    // ================================================================
    // What is lambda?
    // ================================================================
    private static void whatIsLambda() {
        // Before
        Comparator<Payment> byAmount = new Comparator<Payment>() {
            public int compare(Payment p1, Payment p2){
                return p1.getAmount().compareTo(p2.getAmount());
            }
        };

        // After Java 8
        Comparator<Payment> byAmountLambda = (p1, p2) -> p1.getAmount().compareTo(p2.getAmount());
    }


    // ================================================================
    // Predefined Functional Interfaces
    // ================================================================
    private static void predefinedFunctionalInterfaces() {
        Payment payment = new Payment(new BigDecimal("20.37"), "USD", PaymentStatus.NEW);

        Predicate<Payment> newPredicate = p -> p.getPaymentStatus() == PaymentStatus.NEW;
        newPredicate.test(payment); // returns true or false -> true

        Consumer<Payment> paymentConsumer = p -> System.out.println(p);
        paymentConsumer.accept(payment); // takes payment and consume (print) -> print Payment{amount=20.37, currency='USD', paymentStatus=NEW}

        Supplier<Payment> paymentSupplier = () -> new Payment(BigDecimal.ZERO, "USD", PaymentStatus.NEW);
        paymentSupplier.get(); // without parameters. returns payment

        Function<BigDecimal, Payment> paymentProvider = amount -> new Payment(amount, "USD", PaymentStatus.NEW);
        paymentProvider.apply(new BigDecimal("12.34")); // mix of Consumer and Supplier, takes one parameter and returns another
    }

    // ================================================================
    // Custom Functional Interface
    // ================================================================
    private static void customFunctionalInterface() {
        Payment paymentToHandle = new Payment(BigDecimal.ZERO, "USD", PaymentStatus.NEW);

        Long userId = 123L;
        LocalDate date = LocalDate.now();


//        if (paymentToHandle.getPaymentStatus() == PaymentStatus.NEW) {
//            createPayment(paymentToHandle, userId, date);
//        } else if (paymentToHandle.getPaymentStatus() == PaymentStatus.SUSPENDED) {
//            stopPayment(paymentToHandle, userId, date);
//        }
//
        Map<PaymentStatus, PaymentHandler> statusHandlerMap = Map.of(
                PaymentStatus.NEW, (payment, user, localDate) -> createPayment(payment, user, localDate),
                PaymentStatus.SUSPENDED, (payment, user, localDate) -> stopPayment(payment, user, localDate)
        );


        PaymentHandler paymentHandler = statusHandlerMap.get(paymentToHandle.getPaymentStatus());
        paymentHandler.handle(paymentToHandle, userId, date);
    }

    @FunctionalInterface
    interface PaymentHandler {
        void handle(Payment payment, Long userId, LocalDate date);
    }

    private static void stopPayment(Payment payment, Long userId, LocalDate localDate) {
        // ...
    }

    private static void createPayment(Payment payment, Long userId, LocalDate localDate) {
        // ...
    }


    // ================================================================
    // Tips
    // ================================================================
    // * avoid writing a large lambda expression - an anti-pattern
    // * avoid nested generic - Function<PaymentStatus, Map<String, String>>



}
