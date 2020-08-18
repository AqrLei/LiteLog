package com.aqrlei.litelog.control

import android.util.Log
import com.aqrlei.litelog.LogLevel
import com.aqrlei.litelog.printer.ConsoleLogPrinter
import com.aqrlei.litelog.printer.ILogPrinter
import java.text.SimpleDateFormat
import java.util.*

/**
 * created by AqrLei on 2020/5/19
 */

private const val TIME_FORMAT_LAST_MILLIS = "yyyy-MM-dd HH:mm:ss.SSS"

class LogControl : ILogControl {

    override fun buildPrintTag(
        logPrinter: ILogPrinter,
        level: Int,
        globalTag: String,
        moduleName: String,
        tagModel: String): String {
        return StringBuilder().apply {
            val isConsoleLogPrinter = logPrinter is ConsoleLogPrinter
            if (!isConsoleLogPrinter) {
                append(getCurrentTime()+" ")
                append(getLevelString(level) + "/")
            }
            append("${globalTag}-${moduleName}-${tagModel}")
            if (!isConsoleLogPrinter) {
                append(": ")
            }
        }.toString()
    }

    private fun getCurrentTime(): String {
        return SimpleDateFormat(
            TIME_FORMAT_LAST_MILLIS,
            Locale.getDefault()).format(Date())
    }

    private fun getLevelString(level: Int): String {
        return when (level) {
            LogLevel.VERBOSE -> "v"
            LogLevel.DEBUG -> "i"
            LogLevel.INFO -> "i"
            LogLevel.WARN -> "w"
            LogLevel.ERROR -> "e"
            LogLevel.ASSERT -> "a"
            else -> ""
        }
    }


    override fun buildPrintMessage(message: String, tr: Throwable?): String {
        return "$message\n${Log.getStackTraceString(tr)}"
    }

    override fun enabled(
        configEnabled: Boolean,
        configLevel: Int,
        printLevel: Int,
        logPrinters: Set<ILogPrinter>): Boolean {
        return configEnabled && (printLevel >= configLevel) && logPrinters.isNotEmpty()
    }
}