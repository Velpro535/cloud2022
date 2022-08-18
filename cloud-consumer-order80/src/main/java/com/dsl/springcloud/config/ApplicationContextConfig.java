package com.dsl.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced //注释是因为想尝试自定义的负载均衡算法
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
