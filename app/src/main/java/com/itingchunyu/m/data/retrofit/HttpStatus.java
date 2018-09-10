package com.itingchunyu.m.data.retrofit;

/**
 * @author liyanxi
 * @date 2018/8/14
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public interface HttpStatus {

    /**
     * http or https request success code
     */
    int HTTP_REPONSE_CODE = 200;
    /**
     * 登录失效
     */
    int ERROR_CODE_AUTH_FAIL = -11;

    /**
     * 网络链接异常
     */
    String ERROR_CODE_NET_FAIL = "-1001";
}
