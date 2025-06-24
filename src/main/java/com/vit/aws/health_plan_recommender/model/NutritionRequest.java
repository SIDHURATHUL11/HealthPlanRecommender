package com.vit.aws.health_plan_recommender.model;

import com.vit.aws.health_plan_recommender.constants.HealthIssue;

public class NutritionRequest {

    private String name;
    private String emailId;
    private int age;
    private String gender;
    private HealthIssue healthIssue;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public HealthIssue getHealthIssue() {
        return healthIssue;
    }

    public void setHealthIssue(HealthIssue healthIssue) {
        this.healthIssue = healthIssue;
    }

    @Override
    public String toString() {
        return "NutritionRequest{" +
                "name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
