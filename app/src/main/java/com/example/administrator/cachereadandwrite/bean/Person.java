package com.example.administrator.cachereadandwrite.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/5.
 */

public class Person implements Serializable {
    public Person() {
    }

    public Person(String name, int age, String address, String education, String tel, String sex) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.education = education;
        this.tel = tel;
        this.sex = sex;
    }

    /**
     *
     */

    private static final long serialVersionUID = 1L;
    String name;
    int age;
    String address;
    String education;
    String tel;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    String sex;

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", address=" + address
                + ", education=" + education + ", tel=" + tel + ", sex=" + sex
                + "]";
    }
}
