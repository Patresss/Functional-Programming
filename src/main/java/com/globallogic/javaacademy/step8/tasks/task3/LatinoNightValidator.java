package com.globallogic.javaacademy.step8.tasks.task3;

import com.globallogic.javaacademy.step8.tasks.task3.exception.DiscoClubException;
import com.globallogic.javaacademy.step8.tasks.task3.model.Client;
import com.globallogic.javaacademy.step8.tasks.task3.model.ClientStatus;
import com.globallogic.javaacademy.step8.tasks.task3.model.Gender;

public class LatinoNightValidator {

    public void validateClient(final Client client) {
        final ValidatorChain validator = new ValidatorChain("Latino night cheaper ticket validation");
        validator.validate(() -> validateStatus(client));
        validator.validate(() -> validateGender(client));
        validator.validate(() -> validateAge(client));
        validator.getResult();
    }

    private void validateStatus(final Client client) {
        if (client.getClientStatus() != ClientStatus.VIP) {
            throw new DiscoClubException("Client status should be VIP");
        }
    }

    private void validateGender(final Client client) {
        if (client.getGender() != Gender.FEMALE) {
            throw new DiscoClubException("Gender should be female");
        }
    }

    private void validateAge(final Client client) {
        if (client.getAge() > 20) {
            throw new DiscoClubException("Age should be less than 20");
        }
    }


}
