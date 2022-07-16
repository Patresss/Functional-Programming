package com.globallogic.javaacademy.step8.tasks.task4.fee;

import java.math.BigDecimal;

public class FeeApp {

    private static final InflationProvider inflationProvider = new InflationProvider();

    public static void main(String[] args) {
        final TicketService ticketService = new TicketService(inflationProvider);

        final Ticket ticket = new Ticket(BigDecimal.valueOf(37.21), TicketType.PREMIUM);
        final BigDecimal fee = ticketService.calculateFee(ticket);
        System.out.println(fee);
    }
}
