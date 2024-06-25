package com.bookhive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class OauthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthServiceApplication.class);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplateOauth() {
        return new RestTemplate();
    }
}
