package com.dsl.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-------------PaymentFallbackService fall paymentInfo_OK tut";
    }

    @Override
    public String paymentInfo_TIMEOUT(Integer id) {
        return "--------------PaymentFallbackService fall paymentInfo_TIMEOUT tut";
    }
}
