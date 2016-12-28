package com.asiainfo.myapplication.animations;

import android.support.annotation.ColorRes;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/5 0005.
 */

public class Sample implements Serializable{

    public int getColor() {
        return color;
    }

    final int color;

    public String getName() {
        return name;
    }

    private final String name;

    Sample(@ColorRes int color, String name){
        this.color = color;
        this.name = name;
    }


}
