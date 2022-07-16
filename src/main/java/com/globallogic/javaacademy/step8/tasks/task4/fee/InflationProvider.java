package com.globallogic.javaacademy.step8.tasks.task4.fee;

import java.math.BigDecimal;
import java.util.Random;

public class InflationProvider {

    public BigDecimal provideInflation() {
        return BigDecimal.valueOf(new Random().nextDouble());
    }
}
