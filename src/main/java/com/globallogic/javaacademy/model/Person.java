package com.globallogic.javaacademy.model;

import java.util.List;

public class Person {

    private final String name;
    private final String surname;
    private final int age;
    private final List<Address> addresses;

    public Person(String name, String surname, int age, List<Address> addresses) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.addresses = addresses;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", addresses=" + addresses +
                '}';
    }
}
