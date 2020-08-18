package com.aqrlei.litelog.engine

/**
 * created by AqrLei on 2020/5/19
 */
object LogEngineFactory {

    private val logEngineMap = mutableMapOf<String, ILogEngine>()
    private var appModuleName: String = ""
    fun createAppLogEngine(moduleName: String): ILogEngine {
        return LogEngine(moduleName).apply {
            appModuleName = moduleName
            logEngineMap[moduleName] = this
        }
    }

    fun getAppLogEngine(): ILogEngine? = logEngineMap[appModuleName]

    fun getLogEngine(moduleName: String): ILogEngine {
        return logEngineMap[moduleName] ?: LogEngine(moduleName).apply {
            logEngineMap[moduleName] = this
        }
    }
}