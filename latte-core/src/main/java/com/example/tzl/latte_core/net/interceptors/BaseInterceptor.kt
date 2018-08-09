package com.example.tzl.latte_core.net.interceptors

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Create by 田志亮 on 2018/8/9
 */
open class BaseInterceptor :Interceptor{


    override fun intercept(chain: Interceptor.Chain?): Response? {
        return null
    }

    protected fun getUrlParameters(chain: Interceptor.Chain?):LinkedHashMap<String,String>{
        val url:HttpUrl=chain!!.request().url()
        val size=url.querySize()
        val params=LinkedHashMap<String,String>()
        for (i in 0 until size){
            params[url.queryParameterName(i)] = url.queryParameterValue(i)
        }
        return params
    }

    protected fun getUrlParamsters(chain: Interceptor.Chain,key:String): String? {
        val request:Request=chain.request()
        return request.url().queryParameter(key)
    }
}