package com.vit.aws.health_plan_recommender.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("hello/world")
    public String doHelloWorld(){
        return "Hello World";
    }
}
