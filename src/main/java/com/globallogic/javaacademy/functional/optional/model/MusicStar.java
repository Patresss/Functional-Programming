package com.globallogic.javaacademy.functional.optional.model;

import com.globallogic.javaacademy.model.Address;

public class MusicStar {

    private final String name;
    private final String surname;
    private final Address address;

    public MusicStar(String name, String surname, Address address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Address getAddress() {
        return address;
    }
}
