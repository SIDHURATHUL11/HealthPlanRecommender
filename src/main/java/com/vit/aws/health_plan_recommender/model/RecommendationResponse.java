package com.vit.aws.health_plan_recommender.model;

import java.util.Map;

public class RecommendationResponse {

    private String name;
    private String emailId;
    private Map<String, String> recommendations;

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

    public Map<String, String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Map<String, String> recommendations) {
        this.recommendations = recommendations;
    }
}
