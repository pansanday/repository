package com.pandaria.learn.javaxval;

import javax.validation.constraints.*;

public class Member {
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull
    @Size(min = 9, max = 14)
    private String phone;

    @Pattern(regexp = ".+@.+\\.[a-z]+")
    private String email;

    @AssertTrue
    private boolean working;

    @Size(min = 10, max = 200, message = "Number of characters should be in between 10 and 200 inclusive")
    private String aboutMe;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be more than 150")
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
