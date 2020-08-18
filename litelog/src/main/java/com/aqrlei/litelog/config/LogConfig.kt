package com.aqrlei.litelog.config

import android.content.Context
import com.aqrlei.litelog.LogLevel
import java.lang.ref.WeakReference

/**
 * created by AqrLei on 2020/5/19
 */
class LogConfig private constructor(private val ct: WeakReference<Context?>) : ILogConfig {
    companion object {
        fun create(context: Context?) = LogConfig(WeakReference(context))
    }

    override var reference: WeakReference<Context?> = ct

    override var level: Int = LogLevel.VERBOSE

    override var globalTag: String = ""

    override var enable: Boolean = true
}