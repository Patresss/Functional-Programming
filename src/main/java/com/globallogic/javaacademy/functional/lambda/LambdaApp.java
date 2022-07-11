package com.globallogic.javaacademy.functional.lambda;

import com.globallogic.javaacademy.model.Payment;
import com.globallogic.javaacademy.model.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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


        Collections.sort(List.of(), byAmount);

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
        final Payment paymentToHandle = new Payment(BigDecimal.ZERO, "USD", PaymentStatus.NEW);

        final Long userId = 123L;


//        if (paymentToHandle.getPaymentStatus() == PaymentStatus.NEW) {
//            createPayment(paymentToHandle, userId);
//        } else if (paymentToHandle.getPaymentStatus() == PaymentStatus.SUSPENDED) {
//            stopPayment(paymentToHandle, userId);
//        }
//
        final Map<PaymentStatus, PaymentHandler> statusHandlerMap = Map.of(
                PaymentStatus.NEW, (payment, user) -> createPayment(payment, user),
                PaymentStatus.SUSPENDED, (payment, user) -> stopPayment(payment, user)
        );

        final PaymentHandler paymentHandler = statusHandlerMap.get(paymentToHandle.getPaymentStatus());
        paymentHandler.handle(paymentToHandle, userId);
    }

    @FunctionalInterface
    interface PaymentHandler {
        void handle(Payment payment, Long userId);
    }

    private static void stopPayment(Payment payment, Long userId) {
        // ...
    }

    private static void createPayment(Payment payment, Long userId) {
        // ...
    }


    // ================================================================
    // Tips
    // ================================================================
    // * avoid writing a large lambda expression - an anti-pattern
    // * avoid nested generic - Function<PaymentStatus, Map<String, String>>



}
