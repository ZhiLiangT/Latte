package com.example.tzl.latte_core.app

import java.lang.RuntimeException
import kotlin.collections.HashMap

/**
 * Create by 田志亮 on 2018/8/5
 */
class Configurator private constructor() {

    companion object {
        private var LATTE_CONFIGS=HashMap<String,Any>()

        fun getInstance():Configurator{
            return Holder.INSTANCE
        }
    }

    init {
        LATTE_CONFIGS[ConfigType.CONFIG_READY.name] = false
    }

    private  class Holder{
        companion object {
            var INSTANCE=Configurator()
        }
    }

    fun getLatteConfigs():HashMap<String,Any>{
        return LATTE_CONFIGS
    }

    /**
     * 初始化配置成功
     */
    fun configure(){
        LATTE_CONFIGS[ConfigType.CONFIG_READY.name] = true
    }

    /**
     * 配置host地址
     */
    fun withApiHost(host:String):Configurator{
        LATTE_CONFIGS[ConfigType.API_HOST.name]=host
        return this
    }

    /**
     * 检查是否配置完成，没有完成抛出异常
     */
    fun checkConfiguration(){
        val isReady:Boolean= LATTE_CONFIGS[ConfigType.CONFIG_READY.name] as Boolean
        if (!isReady){
            throw RuntimeException("Configuration in not ready,call configure ")
        }
    }

    @SuppressWarnings("unchecked")
    fun getConfiguration(key :Enum<ConfigType>): Any? {
        checkConfiguration()
        return LATTE_CONFIGS[key.name]
    }
}