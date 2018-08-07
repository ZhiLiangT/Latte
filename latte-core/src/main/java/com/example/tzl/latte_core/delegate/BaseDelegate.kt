package com.example.tzl.latte_core.delegate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment

/**
 * Create by 田志亮 on 2018/8/5
 */
abstract class BaseDelegate : SwipeBackFragment() {

    abstract fun setLayout(): Any

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View? = null
        if (setLayout() is Int) {
            rootView = inflater.inflate(setLayout() as Int, container, false)
        } else if (setLayout() is View) {
            rootView = setLayout() as View
        }
        return rootView
    }
}
