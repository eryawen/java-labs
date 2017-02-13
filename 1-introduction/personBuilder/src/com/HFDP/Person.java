package com.HFDP;

/**
 * Created by Monika_ on 2016-10-07.
 */
public class Person {
    private String name;
    private String surname;
    private int age;
    private String pesel;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return "name: "+name+", surname: " + surname+", age: "+age+", pesel: "+ pesel + "\n";
    }

    public static class PersonBuilder{
        private Person person = new Person();

        public Person build() {
            return person;
        }

        public PersonBuilder withName(String name) {
            person.setName(name);
            return this;
        }

        public PersonBuilder withSurname(String surname) {
            person.setSurname(surname);
            return this;
        }

        public PersonBuilder withAge(int age) {
            person.setAge(age);
            return this;
        }

        public PersonBuilder withPesel(String pesel) {
            person.setPesel(pesel);
            return this;
        }
    }
}
