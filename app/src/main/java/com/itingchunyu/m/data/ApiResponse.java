package com.itingchunyu.m.data;

/**
 * 前后端接口通信定义接口数据格式
 *
 * @author liyanxi
 * @date 2018/8/10
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public class ApiResponse<T> {

    /**
     * Response Code 200
     */
    public T data;

    /** Response Code != 200
     * status : 7003000 异常状态码
     * error : 请输入正确的手机号码
     * exception : com.ccmtjf.thor.exception.ThorException
     * timestamp : 1535080779733
     */
    public String status;
    public String error;
    public String message;
    public String exception;
    public String timestamp;


}
