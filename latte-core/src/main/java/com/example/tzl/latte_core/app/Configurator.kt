package com.example.tzl.latte_core.app

import okhttp3.Interceptor
import java.lang.RuntimeException
import kotlin.collections.HashMap

/**
 * Create by 田志亮 on 2018/8/5
 */
class Configurator private constructor() {

    companion object {
        private var LATTE_CONFIGS=HashMap<Any,Any>()
        private var INTERCEPTORS= mutableListOf<Interceptor>()
        fun getInstance():Configurator=Holder.INSTANCE
    }

    init {
        LATTE_CONFIGS[ConfigType.CONFIG_READY] = false
    }

    /**单例*/
    private  object Holder{
        var INSTANCE=Configurator()
    }

    fun getLatteConfigs():HashMap<Any,Any>{
        return LATTE_CONFIGS
    }

    /**
     * 初始化配置成功
     */
    fun configure(){
        LATTE_CONFIGS[ConfigType.CONFIG_READY] = true
    }

    /**
     * 配置host地址
     */
    fun withApiHost(host:String):Configurator{
        LATTE_CONFIGS[ConfigType.API_HOST]=host
        return this
    }

    fun withInterceptor(interceptor: Interceptor):Configurator{
        INTERCEPTORS.add(interceptor)
        Latte.getConfigurations()[ConfigType.INTERCEPTORS]= INTERCEPTORS
        return this
    }

    fun withInterceptors(interceptors: ArrayList<Interceptor>):Configurator{
        INTERCEPTORS.addAll(interceptors)
        Latte.getConfigurations()[ConfigType.INTERCEPTORS]= INTERCEPTORS
        return this
    }

    /**
     * 检查是否配置完成，没有完成抛出异常
     */
    fun checkConfiguration(){
        val isReady:Boolean= LATTE_CONFIGS[ConfigType.CONFIG_READY] as Boolean
        if (!isReady){
            throw RuntimeException("Configuration in not ready,call configure ")
        }
    }

    @SuppressWarnings("unchecked")
    fun getConfiguration(key :Enum<ConfigType>): Any? {
        checkConfiguration()
        return LATTE_CONFIGS[key]
    }
}