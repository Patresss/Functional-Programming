package com.globallogic.javaacademy.functional.step8.tasks.task4;

import java.math.BigDecimal;
import java.util.Random;

public class InflationProvider {

    public BigDecimal provideInflation() {
        return BigDecimal.valueOf(new Random().nextDouble());
    }
}
