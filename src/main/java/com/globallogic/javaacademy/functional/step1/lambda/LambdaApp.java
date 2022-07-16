package com.globallogic.javaacademy.functional.step1.lambda;

import com.globallogic.javaacademy.model.Payment;
import com.globallogic.javaacademy.model.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @Test
    public void whatIsLambda() {
        final String versionFromMethod = providerVersion(4);
        System.out.println(versionFromMethod);

        // ---------------------------------------------------\
        // Function: Take object -> Return object
        // ---------------------------------------------------/
        final Function<Integer, String> versionProvider = versionNumber -> "Version-" + versionNumber;
        final String versionFromLambda = versionProvider.apply(5);
        System.out.println(versionFromLambda);
    }

    private static String providerVersion(final Integer versionNumber) {
        return "Version-" + versionNumber;
    }


    // ================================================================
    // Predefined Functional Interfaces
    // ================================================================

    // ---------------------------------------------------\
    // Predicate: Take object -> Return boolean
    // ---------------------------------------------------/
    @Test
    public void predicateExample() {
        final Payment payment = new Payment(new BigDecimal("20.37"), "USD", PaymentStatus.NEW);

        final boolean isNewFromMethod = isNew(payment);
        System.out.println(isNewFromMethod);

        final Predicate<Payment> isNewChecker = p -> p.getPaymentStatus() == PaymentStatus.NEW;
        final boolean isNewFromPredicate = isNewChecker.test(payment);
        System.out.println(isNewFromPredicate);
    }

    private boolean isNew(final Payment payment) {
        return payment.getPaymentStatus() == PaymentStatus.NEW;
    }

    // ---------------------------------------------------\
    // Consumer: Take object -> Return void <nothing>
    // ---------------------------------------------------/
    @Test
    public void consumerExample() {
        final Payment payment = new Payment(new BigDecimal("20.37"), "USD", PaymentStatus.NEW);

        printCurreny(payment);

        // Take object -> Return void <nothing>
        final Consumer<Payment> currencyPrinter = p -> System.out.println(p.getCurrency());
        currencyPrinter.accept(payment);
    }

    private void printCurreny(final Payment payment) {
        System.out.println(payment.getCurrency());
    }

    // ---------------------------------------------------\
    // Supplier: Take nothing -> Return object
    // ---------------------------------------------------/
    @Test
    public void SupplierExample() {
        final Payment payment = createEmptyPayment();
        System.out.println(payment);

        final Supplier<Payment> emptyPaymentCreator = () -> new Payment(BigDecimal.ZERO, "USD", PaymentStatus.NEW);
        final Payment paymentFromSupplier = emptyPaymentCreator.get();
        System.out.println(paymentFromSupplier);
    }

    private Payment createEmptyPayment() {
        return new Payment(BigDecimal.ZERO, "USD", PaymentStatus.NEW);
    }


    // ---------------------------------------------------\
    // Runnable: Take nothing -> Return nothing
    // ---------------------------------------------------/
    @Test
    public void RunnableExample() {
        printCurrentTime();

        final Runnable currentTimePrinter = () -> System.out.println(LocalDateTime.now());
        currentTimePrinter.run();
    }

    private void printCurrentTime() {
        System.out.println(LocalDateTime.now());
    }


    // ================================================================
    // Custom Functional Interface
    // ================================================================
    private static final Map<PaymentStatus, PaymentHandler> statusHandlerMap = Map.of(
            PaymentStatus.NEW, (payment, user, processedDate) -> createPayment(payment, user, processedDate),
            PaymentStatus.SUSPENDED, (payment, user, processedDate) -> stopPayment(payment, user, processedDate)
    );

    private static void customFunctionalInterface() {
        final Payment paymentToHandle = new Payment(BigDecimal.ZERO, "USD", PaymentStatus.NEW);

        final Long userId = 123L;
        final LocalDateTime dateTime = LocalDateTime.now();

//        if (paymentToHandle.getPaymentStatus() == PaymentStatus.NEW) {
//            createPayment(paymentToHandle, userId, dateTime);
//        } else if (paymentToHandle.getPaymentStatus() == PaymentStatus.SUSPENDED) {
//            stopPayment(paymentToHandle, userId, dateTime);
//        }

        final PaymentHandler paymentHandler = statusHandlerMap.get(paymentToHandle.getPaymentStatus());
        paymentHandler.handle(paymentToHandle, userId, dateTime);
    }

    @FunctionalInterface
    interface PaymentHandler {
        void handle(Payment payment, Long userId, LocalDateTime dateTime);
    }

    private static void stopPayment(final Payment payment, final Long userId, final LocalDateTime dateTime) {
        // ...
    }

    private static void createPayment(final Payment payment, final Long userId, final LocalDateTime dateTime) {
        // ...
    }


    // ================================================================
    // Tips
    // ================================================================
    // * avoid writing a large lambda expression - an anti-pattern
    // * avoid nested generic - Function<PaymentStatus, Map<String, String>>


}
