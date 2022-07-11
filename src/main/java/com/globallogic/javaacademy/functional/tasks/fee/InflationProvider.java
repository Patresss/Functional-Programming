package com.globallogic.javaacademy.functional.tasks.fee;

import java.math.BigDecimal;
import java.util.Random;

public class InflationProvider {

    public BigDecimal provideInflation() {
        return BigDecimal.valueOf(new Random().nextDouble());
    }
}
