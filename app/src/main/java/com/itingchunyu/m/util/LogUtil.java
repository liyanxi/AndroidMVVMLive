package com.itingchunyu.m.util;

import android.text.TextUtils;
import android.util.Log;

import com.itingchunyu.m.BuildConfig;

/**
 * 简易日志工具类
 *
 * @author liyanxi
 * @date 2018/8/14
 * Copyright (c) 2018 www.itingchunyu.com. All rights reserved.
 */
public class LogUtil {

    private static final String TAG = "LogUtil";
    /**
     * set to false when app release.
     */
    private static boolean DEBUG = BuildConfig.DEBUG;

    /**
     * 是否能显示信息
     *
     * @param msg 非空信息
     * @return true or false
     */
    private static boolean canShow(String msg) {
        return DEBUG && !TextUtils.isEmpty(msg);
    }

    public static void i(String msg) {
        if (canShow(msg)) {
            Log.i(TAG, ":=====:" + msg);
        }
    }

    public static void i(String tag, String msg) {
        if (canShow(msg)) {
            Log.i(TAG, ":=====:" + msg);
        }
    }

    public static void d(String msg) {
        if (canShow(msg)) {
            Log.d(TAG, ":=====:" + msg);
        }
    }

    public static void d(Object msg) {
        if (DEBUG) {
            Log.d(TAG, ":=====:" + JsonUtil.serialize(msg));
        }
    }

    public static void d(String tag, String msg) {
        if (canShow(msg)) {
            Log.d(TAG, ":=====:" + msg);
        }
    }

    public static void e(String msg) {
        if (canShow(msg)) {
            Log.e(TAG, ":=====:" + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (canShow(msg)) {
            Log.e(TAG, ":=====:" + msg);
        }
    }
}
