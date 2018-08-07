package com.example.tzl.latte_core.net

import com.example.tzl.latte_core.app.ConfigType
import com.example.tzl.latte_core.app.Latte
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Create by 田志亮 on 2018/8/5
 */
class RestCreator{

    companion object {
        fun getRestService():RestService{
            return RestServiceHolder.REST_SERVICE
        }
        fun getParams():WeakHashMap<String,Any>{
            return ParamsHolder.PARAMS
        }
    }

    private class RetrofitHolder{
        companion object {
            private var BASE_URL:String= Latte.getConfigurations()[ConfigType.API_HOST.name] as String
            var RETROFIT:Retrofit=Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(OHHttpHolder.OK_HTTP_CLIENT)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()
        }
    }

    class OHHttpHolder{
        companion object {
            private var TIME_OUT:Int=60
            var OK_HTTP_CLIENT= OkHttpClient.Builder()
                    .connectTimeout(TIME_OUT.toLong(),TimeUnit.SECONDS)
                    .build()
        }
    }

    class RestServiceHolder{
        companion object {
               var REST_SERVICE=RetrofitHolder.RETROFIT.create(RestService::class.java)
        }
    }

    private class ParamsHolder{
        companion object {
            var PARAMS=WeakHashMap<String,Any>()
        }
    }
}
