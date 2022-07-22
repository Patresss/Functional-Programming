package com.globallogic.javaacademy.functional.step8.tasks.task4;


import java.math.BigDecimal;


public class TicketService {

    private final InflationProvider inflationProvider;

    public TicketService(InflationProvider inflationProvider) {
        this.inflationProvider = inflationProvider;
    }

    public BigDecimal calculateFee(final Ticket ticket){
        return ticket.getTicketType().calculatePrice.apply(this, ticket);
    }
    public BigDecimal calculateStandardFee(final Ticket ticket){
        return BigDecimal.valueOf(1.00);
    }
    public BigDecimal calculatePremiumFee(final Ticket ticket){
        return ticket.getPrice().add(BigDecimal.valueOf(10.0));
    }
    public BigDecimal calculateUltimateFee(final Ticket ticket){
        final BigDecimal inflation = inflationProvider.provideInflation();
        return ticket.getPrice().multiply(inflation);
    }
}
