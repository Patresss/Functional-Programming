package com.globallogic.javaacademy.functional.tasks.fee;

import java.math.BigDecimal;

public class Ticket {

    private final BigDecimal price;
    private final TicketType ticketType;

    public Ticket(BigDecimal price, TicketType ticketType) {
        this.price = price;
        this.ticketType = ticketType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TicketType getTicketType() {
        return ticketType;
    }
}
