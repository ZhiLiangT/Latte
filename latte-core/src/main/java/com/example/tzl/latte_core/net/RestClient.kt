package com.example.tzl.latte_core.net

import com.example.tzl.latte_core.net.callback.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback

/**
 * Create by 田志亮 on 2018/8/5
 */
class RestClient(private val url: String?,
                 private val params: Map<String, Any>,
                 private val request: IRequest?,
                 private val success: ISuccess?,
                 private val failure:IFailure?,
                 private val error: IError?,
                 private val body: ResponseBody?) {
    private val PARAMS = RestCreator.getParams()

    init {
        PARAMS.putAll(params)
    }

    companion object {
        fun builder():RestClientBuilder{
            return RestClientBuilder()
        }
    }

    private fun request(method: HttpMethod){
        val service=RestCreator.getRestService()
        var call:Call<String>?=null
        request?.onRequestStart()
        when(method){
            HttpMethod.GET  -> call=service.get(url,params)
            HttpMethod.POST -> call=service.post(url,params)
            HttpMethod.PUT  -> call=service.put(url,params)
            HttpMethod.DELETE   -> call=service.delete(url,params)
            else -> { }
        }
        if (call!=null){
            call.enqueue(getRequestCallback())
        }
    }

    private fun getRequestCallback():Callback<String>{
        return RequestCallbacks(request,success,failure,error)
    }
    fun get(){
        request(HttpMethod.GET)
    }
    fun post(){
        request(HttpMethod.POST)
    }
    fun put(){
        request(HttpMethod.PUT)
    }
    fun delete(){
        request(HttpMethod.DELETE)
    }
}