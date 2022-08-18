package com.dsl.springcloud.service;

import com.dsl.springcloud.entities.CommonResult;
import com.dsl.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
//这个feign是用在客户端的，所以要更细致的包一层，因为数据是传回服务器端进行处理的
        @GetMapping(value = "/payment/get/{id}")
        public CommonResult getPaymentById(@PathVariable("id") Long id);

        @GetMapping(value = "/payment/feign/timeout")
        public String paymentFeignTimeout();
}
