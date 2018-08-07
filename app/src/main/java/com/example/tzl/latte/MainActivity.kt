package com.example.tzl.latte

import com.example.tzl.latte_core.activities.ProxyActivity
import com.example.tzl.latte_core.delegate.LatteDelegate

class MainActivity : ProxyActivity() {

    override fun setRootDelegate(): LatteDelegate {
        return ExampleDelegate()
    }
}
