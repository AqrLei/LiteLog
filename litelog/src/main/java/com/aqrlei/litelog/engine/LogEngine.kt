package com.aqrlei.litelog.engine

import com.aqrlei.litelog.LogLevel
import com.aqrlei.litelog.config.ILogConfig
import com.aqrlei.litelog.config.LogConfig
import com.aqrlei.litelog.control.ILogControl
import com.aqrlei.litelog.control.LogControl
import com.aqrlei.litelog.printer.ILogPrinter
import com.aqrlei.litelog.printer.PrinterEntity

/**
 * created by AqrLei on 2020/5/19
 */
class LogEngine(private val moduleName: String) : ILogEngine {
    private var logConfig: ILogConfig = LogConfig.create(null)
    private var logControl: ILogControl = LogControl()
    private var logPrinters = mutableSetOf<ILogPrinter>()

    init {
        LogEngineFactory.getAppLogEngine()?.let {
            logConfig = it.logConfig()

            logPrinters.clear()
            logPrinters.addAll(it.logPrinters())

            logControl = it.logControl()
        }
    }

    override fun v(tag: String, msg: String, tr: Throwable?) {
        doPrint(LogLevel.VERBOSE, tag, msg, tr)
    }

    override fun v(msg: String, tr: Throwable?) {
        doPrint(LogLevel.VERBOSE, null, msg, tr)
    }

    override fun d(tag: String, msg: String, tr: Throwable?) {
        doPrint(LogLevel.DEBUG, tag, msg, tr)
    }

    override fun d(msg: String, tr: Throwable?) {
        doPrint(LogLevel.DEBUG, null, msg, tr)

    }

    override fun i(tag: String, msg: String, tr: Throwable?) {
        doPrint(LogLevel.INFO, tag, msg, tr)
    }

    override fun i(msg: String, tr: Throwable?) {
        doPrint(LogLevel.INFO, null, msg, tr)
    }

    override fun w(tag: String, msg: String, tr: Throwable?) {
        doPrint(LogLevel.WARN, tag, msg, tr)
    }

    override fun w(msg: String, tr: Throwable?) {
        doPrint(LogLevel.WARN, null, msg, tr)
    }

    override fun e(tag: String, msg: String, tr: Throwable?) {
        doPrint(LogLevel.ERROR, tag, msg, tr)
    }

    override fun e(msg: String, tr: Throwable?) {
        doPrint(LogLevel.ERROR, null, msg, tr)

    }

    override fun config(logConfig: ILogConfig): ILogEngine {
        this.logConfig = logConfig
        return this
    }

    override fun logControl(): ILogControl = logControl

    override fun logConfig(): ILogConfig = logConfig

    override fun logPrinters(): Set<ILogPrinter> = logPrinters

    override fun setLogPrinters(vararg logPrinter: ILogPrinter): ILogEngine {
        logPrinters.addAll(logPrinter)
        return this
    }

    private fun enabled(@LogLevel level: Int): Boolean {
        return logControl.enabled(logConfig.enable, logConfig.level, level, logPrinters)
    }

    private fun doPrint(@LogLevel level: Int, tagModel: String?, message: String?, tr: Throwable?) {
        if (enabled(level)) {
            print(level, tagModel, message, tr)
        }
    }

    private fun print(@LogLevel level: Int, tagModel: String?, message: String?, tr: Throwable?) {
        for (logPrinter in logPrinters) {
            if (level >= logPrinter.level) {
                val printTag = logControl.buildPrintTag(
                    logPrinter,
                    level,
                    logConfig.globalTag,
                    moduleName,
                    tagModel ?: "default")
                val printMessage = logControl.buildPrintMessage(message ?: "empty", tr)

                logPrinter.print(
                    level,
                    PrinterEntity(tagModel ?: "default", printTag, printMessage))
            }
        }
    }
}
