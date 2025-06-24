package com.vit.aws.health_plan_recommender.constants;

public enum HealthIssue {

    Diabetes("Diabetes"),
    BloodPressure("Blood Pressure"),
    Cholesterol("Cholesterol"),
    Obesity("Obesity"),
    HeartDisease("Heart Disease");

    final String value;

    HealthIssue(String value) {
        this.value = value;
    }
}
