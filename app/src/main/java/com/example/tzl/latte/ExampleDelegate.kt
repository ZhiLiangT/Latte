package com.example.tzl.latte

import com.example.tzl.latte_core.delegate.LatteDelegate

/**
 * Create by 田志亮 on 2018/8/5
 */
class ExampleDelegate : LatteDelegate() {

    override fun setLayout(): Any {
        return R.layout.delegate_example
    }
}
