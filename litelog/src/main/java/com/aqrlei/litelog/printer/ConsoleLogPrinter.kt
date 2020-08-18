package com.aqrlei.litelog.printer

import android.util.Log
import com.aqrlei.litelog.config.ILogPrinterConfig

/**
 * created by AqrLei on 2020/5/19
 */
private const val LOG_LINE_MAX_LENGTH = 2 * 1024

class ConsoleLogPrinter(private val logPrinterConfig: ILogPrinterConfig) : AbstractLogPrinter() {

    override fun print(level: Int, printerEntity: PrinterEntity) {
        val length = printerEntity.message.length
        if (length < LOG_LINE_MAX_LENGTH) {
            Log.println(level, printerEntity.tag, printerEntity.message)
        } else {
            for (i in 0 until length step LOG_LINE_MAX_LENGTH) {
                Log.println(
                    level, printerEntity.tag,
                    if (i + LOG_LINE_MAX_LENGTH < length)
                        printerEntity.message.substring(i, i + LOG_LINE_MAX_LENGTH)
                    else
                        printerEntity.message.substring(i, length))
            }
        }
    }

    override val level: Int
        get() = logPrinterConfig.level
}