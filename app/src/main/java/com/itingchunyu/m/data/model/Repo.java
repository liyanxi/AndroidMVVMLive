package com.itingchunyu.m.data.model;

/**
 * @author liyanxi
 * @date 2018/8/10
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */

public class Repo {
    private int id;
    private String node_id;
    private String name;
    private String full_name;

    @Override
    public String toString() {
        return name+","+full_name;
    }
}
