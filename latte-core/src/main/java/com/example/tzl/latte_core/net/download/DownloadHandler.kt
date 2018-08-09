package com.example.tzl.latte_core.net.download

import com.example.tzl.latte_core.net.RestCreator
import com.example.tzl.latte_core.net.callback.IError
import com.example.tzl.latte_core.net.callback.IFailure
import com.example.tzl.latte_core.net.callback.IRequest
import com.example.tzl.latte_core.net.callback.ISuccess
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Create by 田志亮 on 2018/8/8
 */
class DownloadHandler(private val url: String?,
                      private val downloadDir:String?,
                      private val extension:String?,
                      private val name:String?,
                      private val request: IRequest?,
                      private val success: ISuccess?,
                      private val failure: IFailure?,
                      private val error: IError?) {

    private val PARAMS = RestCreator.getParams()

    fun handlerDownload(){
        if (request!=null){
            request.onRequestStart()
        }
        RestCreator.getRestService().download(url,PARAMS)
                .enqueue(object :Callback<ResponseBody>{

                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                        
                    }

                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {

                    }

                })


    }

}