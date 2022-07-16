package com.globallogic.javaacademy.functional.step2.behaviorparameterization;

import com.globallogic.javaacademy.model.Payment;
import com.globallogic.javaacademy.model.PaymentStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BehaviorParameterizationApp {

    public static void main(String[] args) {
        final var payments = List.of(
                new Payment(new BigDecimal("20.37"), "USD", PaymentStatus.NEW),
                new Payment(new BigDecimal("21.37"), "PLN", PaymentStatus.NEW),
                new Payment(new BigDecimal("14.99"), "PLN", PaymentStatus.SUSPENDED),
                new Payment(new BigDecimal("100.37"), "PLN", PaymentStatus.SUSPENDED),
                new Payment(new BigDecimal("200.37"), "USD", PaymentStatus.CREATED)
        );

        // #1 Client: "We need a new report - Could you write a function that returns only payments in PLN?"
        final List<Payment> plnReport = filterPlnPayments(payments);
        plnReport.forEach(System.out::println);

        // #2 Client: "Looks good! Could you do the same for USD?"
        final List<Payment> customCurrencyReport = filterCurrencyPayments(payments, "USD");
        customCurrencyReport.forEach(System.out::println);

        // #3 Client: "Super, we need one more for new payments"
        final List<Payment> customTypeReport = filterCurrencyTypePayments(payments, "USD", PaymentStatus.NEW);
        customTypeReport.forEach(System.out::println);

        // #4 Client: "we need one more for ..."
        final List<Payment> customReport = filterPayments(payments, payment -> "USD".equals(payment.getCurrency()) && PaymentStatus.NEW == payment.getPaymentStatus());
        customReport.forEach(System.out::println);

    }

    // #1
    private static List<Payment> filterPlnPayments(final List<Payment> payments) {
        final List<Payment> result = new ArrayList<>();
        for (final Payment payment: payments) {
            if ("PLN".equals(payment.getCurrency())) {
                result.add(payment);
            }
        }
        return result;
    }

    // #2
    private static List<Payment> filterCurrencyPayments(final List<Payment> payments, final String currency) {
        final List<Payment> result = new ArrayList<>();
        for (Payment payment: payments) {
            if (currency.equals(payment.getCurrency())) {
                result.add(payment);
            }
        }
        return result;
    }

    // #3
    private static List<Payment> filterCurrencyTypePayments(final List<Payment> payments, final String currency, final PaymentStatus paymentStatus) {
        final List<Payment> result = new ArrayList<>();
        for (Payment payment: payments) {
            if (currency.equals(payment.getCurrency()) && paymentStatus == payment.getPaymentStatus()) {
                result.add(payment);
            }
        }
        return result;
    }

    // #4
    private static List<Payment> filterPayments(final List<Payment> payments, final Predicate<Payment> predicate) {
        final List<Payment> result = new ArrayList<>();
        for (Payment payment: payments) {
            if (predicate.test(payment)) {
                result.add(payment);
            }
        }
        return result;
    }

}
