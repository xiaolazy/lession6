package com.biz.lesson5.school.model;

import lombok.Builder;
import lombok.Data;

/**
 * @program: lession6
 * @description:
 * @author: lyy
 * @generate: 2020-05-28 11:26
 **/
@Data
@Builder
public class ResultBean {
    private Integer code = 200;
    private String msg;
    private Object data;

    public static ResultBean success(String msg){
        return ResultBean.builder().code(200).msg(msg).build();
    }
    public static ResultBean successData(Object data){
        return ResultBean.builder().code(200).data(data).build();
    }
    public static ResultBean fail(String msg){
        return ResultBean.builder().code(500).msg(msg).build();
    }
}
