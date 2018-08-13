package com.example.tzl.latte_core.net

import com.example.tzl.latte_core.net.callback.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import java.io.File

/**
 * Create by 田志亮 on 2018/8/5
 */
class RestClient(private val url: String?,
                 private val params: Map<String, Any>,
                 private val downloadDir:String?,
                 private val extension:String?,
                 private val name:String?,
                 private val request: IRequest?,
                 private val success: ISuccess?,
                 private val failure:IFailure?,
                 private val error: IError?,
                 private val body: RequestBody?,
                 private val file:File?) {

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
            HttpMethod.GET      -> call=service.get(url,params)
            HttpMethod.POST     -> call=service.post(url,params)
            HttpMethod.POST_RAW -> call=service.postRaw(url,body)
            HttpMethod.PUT      -> call=service.put(url,params)
            HttpMethod.PUT_RAW  -> call=service.putRaw(url,body)
            HttpMethod.DELETE   -> call=service.delete(url,params)
            HttpMethod.UPLOAD   ->{
                val requestBody=RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),file)
                val body=MultipartBody.Part.createFormData("file",file!!.name,requestBody)
                call = RestCreator.getRestService().upload(url,body)
            }
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
        if (body==null){
            request(HttpMethod.POST)
        }else{
            if (!params.isEmpty()){
                throw RuntimeException("params must be null!")
            }
            request(HttpMethod.POST_RAW)
        }

    }

    fun put(){
        if (body==null){
            request(HttpMethod.PUT)
        }else{
            if(!params.isEmpty()){
                throw RuntimeException("params must be null!")
            }
            request(HttpMethod.PUT_RAW)
        }
    }
    fun delete(){
        request(HttpMethod.DELETE)
    }

    fun download(){
        request(HttpMethod.UPLOAD)
    }
}