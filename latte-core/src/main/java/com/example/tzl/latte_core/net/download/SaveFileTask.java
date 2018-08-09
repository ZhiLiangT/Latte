package com.example.tzl.latte_core.net.download;

import android.os.AsyncTask;

import com.example.tzl.latte_core.net.callback.IRequest;
import com.example.tzl.latte_core.net.callback.ISuccess;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Create by 田志亮 on 2018/8/8
 */
public class SaveFileTask extends AsyncTask<Object,Void,File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadDir= (String) objects[0];
        String extension= (String) objects[1];
        final ResponseBody  body= (ResponseBody) objects[2];
        final String name= (String) objects[3];
        final InputStream is=body.byteStream();
        if (downloadDir==null||downloadDir.equals("")){
            downloadDir="down_loads";
        }
        if (extension==null || extension.equals("")){
            extension="";
        }
        if (name==null){
        }
        return null;
    }
}
