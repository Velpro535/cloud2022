package com.dsl.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalanced {

    //首先，我们要收集所有活着的Eureka的集群总数，这是一个重要的变量
    ServiceInstance instances(List<ServiceInstance> serviceInstances);


}
