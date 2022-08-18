package com.dsl.myrule;

/*
* 因为特殊化配置不可以在ScanComponent或子包下，所以要自己配置一个包和类
* */

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule(){
        return new RandomRule(); //定义为随机
    }

}
