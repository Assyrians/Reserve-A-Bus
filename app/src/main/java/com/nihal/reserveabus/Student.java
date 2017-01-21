package com.nihal.reserveabus;

/**
 * Created by tawfikkahwaje on 1/21/17.
 */

public class Student {
    private String Id;
    private String name;
    private String email;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student() {

    }

    public Student(String id, String name, String email) {
        Id = id;
        this.name = name;
        this.email = email;
    }

    public Student(String email, String id) {
        this.email = email;
        Id = id;
    }
}
