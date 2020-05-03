package com.andre.ReactJava.entity;

import lombok.*;

@Getter
@Setter
public class Person {

    private String name;
    private String lastName;

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}
