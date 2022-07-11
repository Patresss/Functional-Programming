package com.globallogic.javaacademy.functional.tasks.fee;


import java.math.BigDecimal;


public class TicketService {

    private final InflationProvider inflationProvider;

    public TicketService(InflationProvider inflationProvider) {
        this.inflationProvider = inflationProvider;
    }

    public BigDecimal calculateFee(final Ticket ticket) {
        if (ticket.getTicketType() == TicketType.STANDARD) {
            return BigDecimal.valueOf(1.00);
        } else if (ticket.getTicketType() == TicketType.PREMIUM) {
            return ticket.getPrice().add(BigDecimal.valueOf(10.0));
        } else if (ticket.getTicketType() == TicketType.ULTIMATE) {
            final BigDecimal inflation = inflationProvider.provideInflation();
            return ticket.getPrice().multiply(inflation);
        } else {
            throw new RuntimeException("Invalid ticket status");
        }
    }

}
