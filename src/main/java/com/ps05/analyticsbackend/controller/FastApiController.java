package com.ps05.analyticsbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FastApiController {

    @GetMapping("/fastapi-data")
    public String getFastApiData() {

        String url = "http://127.0.0.1:8000/analytics";

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, String.class);
    }
}