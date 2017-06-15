package com.asiainfo.myapplication.util;

/**
 * Created by Talisman on 2017/6/15.
 */

public class JniUtils {

    static {
        System.loadLibrary("hello-jni");
    }

    public native String getString();

}
