package com.globallogic.javaacademy.model;

import java.math.BigDecimal;

public class Payment {

    private final BigDecimal amount;
    private final String currency;
    private final PaymentStatus paymentStatus;

    public Payment(BigDecimal amount, String currency, PaymentStatus paymentStatus) {
        this.amount = amount;
        this.currency = currency;
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
