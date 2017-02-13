package com.HFDP;

public class Main {

    public static void main(String[] args) {
        Person p = new Person.PersonBuilder()
                .withName("Adam")
                .withSurname("Nowak")
                .withAge(5)
                .withPesel("9609123451")
                .build();
        System.out.println(p);
    }
}
