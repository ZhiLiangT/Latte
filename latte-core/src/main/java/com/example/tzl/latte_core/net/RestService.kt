package com.example.tzl.latte_core.net

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Create by 田志亮 on 2018/8/5
 */
interface RestService {

    @GET
    fun get(@Url url:String?,@QueryMap params:@JvmSuppressWildcards Map<String, Any>): Call<String>

    @FormUrlEncoded
    @POST
    fun post(@Url url:String?,@FieldMap params:Map<String,Any>): Call<String>

    @POST
    fun postRaw(@Url url:String?,@Body body: RequestBody?):Call<String>

    @FormUrlEncoded
    @PUT
    fun put(@Url ur:String?,@FieldMap params:Map<String,Any>):Call<String>

    @PUT
    fun putRaw(@Url url: String?,@Body body: RequestBody?):Call<String>

    @DELETE
    fun delete(@Url url:String?,@QueryMap params:Map<String,Any>): Call<String>

    @Streaming
    @GET
    fun download(@Url url:String?,@QueryMap params:Map<String,Any>?): Call<ResponseBody>

    @Multipart
    @POST
    fun upload(@Url url:String?,@Part file:MultipartBody.Part?): Call<String>

}