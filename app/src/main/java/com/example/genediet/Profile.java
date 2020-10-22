package com.example.genediet;

public class Profile {
    public String age ;
    public String genetype;
    public String name;
    public String weight;

    public Profile(){

    }

    public Profile(String age, String genetype, String name, String weight) {
        this.age = age;
        this.genetype = genetype;
        this.name = name;
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGenetype() {
        return genetype;
    }

    public void setGenetype(String genetype) {
        this.genetype = genetype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
