package com.dsl.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池：" + Thread.currentThread().getName() +"paymentInfo_OK,id:" + id +"\t"+"O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return "线程池：" + Thread.currentThread().getName() +"paymentInfo_TimeOut,id:" + id +"\t"+"O(∩_∩)O哈哈~";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName() +"paymentInfo_TimeOutHandler,id:" + id +"\t"+"┭┮﹏┭┮";
    }


    //-------服务熔断(过了窗口期会自动恢复)
    @HystrixCommand(groupKey="groupa",fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("*****id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();//等价于UUID.randomUUID().toString

        return Thread.currentThread().getName() + "\t" + "调用成功,流水号" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后再试，id："+id;
    }
/*

    //参考:HystrixCommandProperties类,函数的默认执行时间为1000ms,如果运行时间超过1000ms则出错(default_executionTimeoutInMilliseconds = 1000)
    //groupKey的作用是当一组服务中有一个被熔断,同组的也会熔断,如果不设置,则默认为空,如果有一个熔断,其他都会熔断
    @HystrixCommand(groupKey="groupb",fallbackMethod = "paymentCircuitWithoutParaBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker2(){
        try {
            //运行异常
            int timeNumer = 1;
//            TimeUnit.SECONDS.sleep(timeNumer);
            TimeUnit.MILLISECONDS.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "success  paymentInfo_TimeOut" +"\t";
    }
*/


}
