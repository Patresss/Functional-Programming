package com.globallogic.javaacademy.functional.tasks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class Immutable {

    public static void main(String[] args) {
        Date dateOfBirth = new Date();
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address());
        addresses.add(new Address());

        ImmutablePerson person = new ImmutablePerson();
        person.setName("Patryk");
        person.setSurname("Smith");
        person.setDateOfBirth(dateOfBirth);
        person.setAddresses(addresses);

        System.out.println(person);

        // Whatever I do here, the result /\  \/ should be the same

        System.out.println(person);

    }

    static class ImmutablePerson {

        private String name;
        private String surname;
        private List<Address> addresses;
        private Date dateOfBirth;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public List<Address> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Address> addresses) {
            this.addresses = addresses;
        }

        public Date getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        @Override
        public String toString() {
            return "ImmutablePerson{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", dateOfBirth='" + dateOfBirth + '\'' +
                    ", addresses=" + addresses +
                    '}';
        }

    }


    static class Address {

    }

    static class ImmutablePersonSolution {

        private final String name;
        private final String surname;
        private final List<Address> addresses;
        private final Date dateOfBirth;

        ImmutablePersonSolution(String name, String surname, List<Address> addresses, Date dateOfBirth) {
            this.name = name;
            this.surname = surname;
            this.addresses = new ArrayList<>(addresses);
            this.dateOfBirth = new Date(dateOfBirth.getTime());
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public List<Address> getAddresses() {
            return new ArrayList<>(addresses);
        }

        public Date getDateOfBirth() {
            return new Date(dateOfBirth.getTime());
        }

        @Override
        public String toString() {
            return "ImmutablePerson{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", dateOfBirth='" + dateOfBirth + '\'' +
                    ", addresses=" + addresses +
                    '}';
        }
    }
}