package com.example.tzl.latte_core.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.ContentFrameLayout
import com.example.tzl.latte_core.R
import com.example.tzl.latte_core.delegate.LatteDelegate

import me.yokeyword.fragmentation.SupportActivity

/**
 * Create by 田志亮 on 2018/8/5
 */
abstract class ProxyActivity : SupportActivity(){

    abstract fun setRootDelegate():LatteDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContainer(savedInstanceState)
    }

    @SuppressLint("RestrictedApi")
    private fun initContainer(savedInstanceState: Bundle?){
        val container=ContentFrameLayout(this)
        container.id= R.id.container_frame_id
        setContentView(container)
        if (savedInstanceState==null){
            loadRootFragment(R.id.container_frame_id,setRootDelegate())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        System.gc()
        System.runFinalization()
    }
}
