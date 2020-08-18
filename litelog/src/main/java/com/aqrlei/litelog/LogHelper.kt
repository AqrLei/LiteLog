package com.aqrlei.litelog

import com.aqrlei.litelog.engine.ILogEngine
import com.aqrlei.litelog.engine.LogEngineFactory

/**
 * created by AqrLei on 2020/5/19
 */
object LogHelper {
    fun initAppModule(appModule: String): ILogEngine {
        return LogEngineFactory.createAppLogEngine(appModule)
    }

    @JvmStatic
    fun module(appModule: String) = LogEngineFactory.getLogEngine(appModule)

    fun v(tag: String, msg: String, tr: Throwable? = null) {
        LogEngineFactory.getAppLogEngine()?.v(tag, msg, tr)
    }

    fun v(msg: String, tr: Throwable? = null) {
        LogEngineFactory.getAppLogEngine()?.v(msg, tr)
    }

    fun d(tag: String, msg: String, tr: Throwable? = null) {
        LogEngineFactory.getAppLogEngine()?.d(tag, msg, tr)
    }

    fun d(msg: String, tr: Throwable? = null) {
        LogEngineFactory.getAppLogEngine()?.d(msg, tr)
    }

    fun i(tag: String, msg: String, tr: Throwable? = null) {
        LogEngineFactory.getAppLogEngine()?.i(tag, msg, tr)
    }

    fun i(msg: String, tr: Throwable? = null) {
        LogEngineFactory.getAppLogEngine()?.i(msg, tr)
    }

    fun w(tag: String, msg: String, tr: Throwable? = null) {
        LogEngineFactory.getAppLogEngine()?.w(tag, msg, tr)
    }

    fun w(msg: String, tr: Throwable? = null) {
        LogEngineFactory.getAppLogEngine()?.w(msg, tr)
    }

    fun e(tag: String, msg: String, tr: Throwable? = null) {
        LogEngineFactory.getAppLogEngine()?.e(tag, msg, tr)
    }

    fun e(msg: String, tr: Throwable? = null) {
        LogEngineFactory.getAppLogEngine()?.e(msg, tr)
    }
}