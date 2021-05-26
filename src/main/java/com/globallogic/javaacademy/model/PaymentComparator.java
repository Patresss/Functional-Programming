package com.globallogic.javaacademy.model;

import java.util.Comparator;

public class PaymentComparator implements Comparator<Payment> {

    @Override
    public int compare(Payment p1, Payment p2) {
        return p1.getAmount().compareTo(p2.getAmount());
    }

}
