package com.globallogic.javaacademy.functional.step8.tasks.task3;

import com.globallogic.javaacademy.functional.step8.tasks.task3.model.Client;
import com.globallogic.javaacademy.functional.step8.tasks.task3.model.ClientStatus;

import static com.globallogic.javaacademy.functional.step8.tasks.task3.model.Gender.MALE;

public class DiscoClubApp {
    private static final LatinoNightValidator latinoNightValidator = new LatinoNightValidator();


    public static void main(String[] args) {
        final Client tom = new Client("Tom", ClientStatus.STANDARD, MALE, 30);
        buyCheaperLatinoNightTicket(tom);
    }

    private static void buyCheaperLatinoNightTicket(final Client client) {
        latinoNightValidator.validateClient(client);
        // ... buy

    }


}
