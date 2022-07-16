package com.globallogic.javaacademy.step8.tasks.task3.model;

public class Client {

    private final String name;
    private final ClientStatus clientStatus;
    private final Gender gender;
    private final int age;


    public Client(String name, ClientStatus clientStatus, Gender gender, int age) {
        this.name = name;
        this.clientStatus = clientStatus;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

}
