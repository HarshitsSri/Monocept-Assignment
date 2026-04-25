package com.monocept.project.app.model;


public class Student {

    private int id;
    private String name;
    private int branchId;   // 🔥 changed
    private int age;

    public Student(int id, String name, int branchId, int age) {
        this.id = id;
        this.name = name;
        this.branchId = branchId;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBranchId() {   // 🔥 changed
        return branchId;
    }

    public int getAge() {
        return age;
    }
}
