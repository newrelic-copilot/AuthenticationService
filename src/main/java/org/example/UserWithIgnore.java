package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserWithIgnore {
    public String name;
    public int age;
    @JsonIgnore
    public String secret;

    public UserWithIgnore() {
        // Default constructor for Jackson
    }

    public UserWithIgnore(String name, int age, String secret) {
        this.name = name;
        this.age = age;
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "UserWithIgnore{name='" + name + "', age=" + age + ", secret='" + secret + "'}";
    }
}