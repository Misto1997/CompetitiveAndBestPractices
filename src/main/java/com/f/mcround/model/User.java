package com.f.mcround.model;

import java.util.Set;

//builder pattern can be used to avoid few non mandatory details
public class User {

    String userName;
    String fullName;
    String profession;
    int age;
    Set<Topic> subscriptionList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Topic> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(Set<Topic> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", profession='" + profession + '\'' +
                ", age=" + age +
                ", subscriptionList=" + subscriptionList +
                '}';
    }
}
