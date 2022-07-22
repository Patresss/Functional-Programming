package com.globallogic.javaacademy.functional.step8.tasks.task4;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public enum TicketType {

    STANDARD(TicketService::calculateStandardFee),
    PREMIUM(TicketService::calculatePremiumFee),
    ULTIMATE(TicketService::calculateUltimateFee);

    public final BiFunction<TicketService, Ticket, BigDecimal> calculatePrice;

    TicketType(BiFunction<TicketService, Ticket, BigDecimal> calculatePrice) {
        this.calculatePrice=calculatePrice;
    }
}
