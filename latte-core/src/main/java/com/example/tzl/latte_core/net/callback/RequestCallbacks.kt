package com.example.tzl.latte_core.net.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Create by 田志亮 on 2018/8/7
 */
class RequestCallbacks(private val REQUEST: IRequest?, private val SUCCESS: ISuccess?, private val FAILURE: IFailure?, private val ERROR: IError?) : Callback<String> {

    override fun onResponse(call: Call<String>, response: Response<String>) {
        if (response.isSuccessful) {
            if (call.isExecuted) {
                SUCCESS?.onSuccess(response.body()!!)
            }
        } else {
            ERROR?.onError(response.code(), response.message())
        }
    }

    override fun onFailure(call: Call<String>, t: Throwable) {
        FAILURE?.onFailure()
        REQUEST?.onRequestEnd()
    }
}
