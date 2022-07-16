package com.globallogic.javaacademy.step8.tasks.task3;

import com.globallogic.javaacademy.step8.tasks.task3.exception.DiscoClubException;

import java.util.ArrayList;
import java.util.List;

public class ValidatorChain {

    private final String name;
    private final List<String> errors = new ArrayList<>();

    public ValidatorChain(String name) {
        this.name = name;
    }

    public void validate(Runnable operationToValidate) {
        try {
            operationToValidate.run();
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    public void getResult() {
        if (!errors.isEmpty()) {
            throw new DiscoClubException(name + " failed, reasons: " + errors);
        }
    }


}
