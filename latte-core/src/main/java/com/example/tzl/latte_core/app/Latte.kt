package com.example.tzl.latte_core.app

import android.content.Context
import java.util.*

object Latte {

    fun init(context: Context):Configurator{
        getConfigurations()[ConfigType.APPLICATION_CONTEXT.name] = context.applicationContext
        return Configurator.getInstance()
    }

    fun getConfigurations():HashMap<String,Any>{
        return Configurator.getInstance().getLatteConfigs()
    }

    fun getApplication():Context{
        return getConfigurations()[ConfigType.APPLICATION_CONTEXT.name] as Context
    }
}