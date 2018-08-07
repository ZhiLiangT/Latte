package com.example.tzl.latte

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.example.tzl.latte_core.net.RestClient
import com.example.tzl.latte_core.net.callback.IError
import com.example.tzl.latte_core.net.callback.IFailure
import com.example.tzl.latte_core.net.callback.ISuccess

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        RestClient.builder()
                .url("http://www.baidu.com")
                .success(object : ISuccess {
                    override fun onSuccess(response: String) {
                        Toast.makeText(this@TestActivity,response,Toast.LENGTH_SHORT).show()
                    }
                })
                .failure(object :IFailure{
                    override fun onFailure() {

                    }
                })
                .error(object :IError{
                    override fun onError(code: Int, msg: String) {

                    }
                })
                .build().get()

    }
}
