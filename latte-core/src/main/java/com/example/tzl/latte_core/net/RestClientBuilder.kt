package com.example.tzl.latte_core.net

import com.example.tzl.latte_core.net.callback.IError
import com.example.tzl.latte_core.net.callback.IFailure
import com.example.tzl.latte_core.net.callback.IRequest
import com.example.tzl.latte_core.net.callback.ISuccess
import okhttp3.MediaType
import okhttp3.ResponseBody
import java.util.*

/**
 * Create by 田志亮 on 2018/8/5
 */
class RestClientBuilder {
    private var mUrl:String?=null
    private var mParams:WeakHashMap<String,Any> = RestCreator.getParams()
    private var mIRequest: IRequest?=null
    private var mISuccess:ISuccess?=null
    private var mIError:IError?=null
    private var mIFailure:IFailure?=null
    private var mBody: ResponseBody?=null

    fun url(url:String):RestClientBuilder{
        this.mUrl=url
        return this
    }

    fun params(params:WeakHashMap<String,Any>):RestClientBuilder{
        mParams.putAll(params)
        return this
    }

    fun params(key:String,value:Any):RestClientBuilder{
        mParams[key]=value
        return this
    }

    fun raw(raw:String):RestClientBuilder{
        this.mBody= ResponseBody.create(MediaType.parse("application.json;char=UTF-8"),raw)
        return this
    }

    fun request(iRequest: IRequest):RestClientBuilder{
        this.mIRequest=iRequest
        return this
    }


    fun success(isuccess:ISuccess):RestClientBuilder{
        this.mISuccess=isuccess
        return this
    }

    fun error(ierror:IError):RestClientBuilder{
        this.mIError=ierror
        return this
    }

    fun failure(ifailure:IFailure):RestClientBuilder{
        this.mIFailure=ifailure
        return this
    }

    fun build():RestClient{
        if (mUrl==null){
            throw RuntimeException("url can't be empty")
        }
        return RestClient(mUrl,mParams,mIRequest,mISuccess,mIFailure,mIError,mBody)
    }

}
