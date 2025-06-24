package com.vit.aws.health_plan_recommender.controller;

import com.vit.aws.health_plan_recommender.aws.DynamoUtil;
import com.vit.aws.health_plan_recommender.aws.SNSUtil;
import com.vit.aws.health_plan_recommender.model.NutritionRequest;
import com.vit.aws.health_plan_recommender.model.RecommendationResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/nutrition")
public class NutritionPlanController {

    @PostMapping("/create")
    public String createNutrition(@RequestBody NutritionRequest request) {
        //TODO neutrition creation needed

        SNSUtil util = new SNSUtil();
        String arn =  util.createSNSTopic(request.getName());
        util.createSubscriptions(arn, request.getEmailId());

        DynamoUtil dynamoUtil = new DynamoUtil();
        dynamoUtil.createNutritionPlan("nutrition-planner", request, arn);

        return "done";
    }

    @GetMapping("/recommendations")
    public RecommendationResponse getRecommendation(@RequestParam(value = "userName") String name, @RequestParam (value = "emailId") String email) {

        DynamoUtil util = new DynamoUtil();
        Map<String, String> data = util.getDynamoDBItem("nutrition-recommendation", name, email);

        RecommendationResponse response = new RecommendationResponse();
        response.setName(name);
        response.setEmailId(email);
        response.setRecommendations(data);
        return response;
    }
}
