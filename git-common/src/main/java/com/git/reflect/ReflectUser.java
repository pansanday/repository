package com.git.reflect;

public class ReflectUser {
    
    private String userName;
    private int age;
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ReflectUser [userName=" + userName + ", age=" + age + "]";
    }
    
}
