package com.globallogic.javaacademy.functional.step8.tasks.task4;


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
    public BigDecimal calculateFeeSwitch(final Ticket ticket) {
        return switch (ticket.getTicketType()) {
            case STANDARD -> BigDecimal.valueOf(1.00);
            case PREMIUM -> ticket.getPrice().add(BigDecimal.valueOf(10.0));
            case ULTIMATE -> {
                final BigDecimal inflation = inflationProvider.provideInflation();
                yield ticket.getPrice().multiply(inflation);
            }
        };
    }

    public BigDecimal calculateFeeEnum(final Ticket ticket){
        return ticket.getTicketType().calculateFee( this, ticket);
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
