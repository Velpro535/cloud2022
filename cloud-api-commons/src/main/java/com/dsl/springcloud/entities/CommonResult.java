package com.dsl.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    //自定义另一情况的传参
    public CommonResult(Integer code, String message){
        this(code,message,null);
    }
}
