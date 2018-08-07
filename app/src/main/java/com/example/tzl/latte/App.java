package com.example.tzl.latte;

import android.app.Application;

import com.example.tzl.latte_core.app.Latte;

/**
 * Create by 田志亮 on 2018/8/5
 */
public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.INSTANCE.init(this)
                .withApiHost("http://www.baidu.com/")
                .configure();
    }
}
