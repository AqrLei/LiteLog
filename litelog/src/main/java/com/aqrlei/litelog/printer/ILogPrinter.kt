package com.aqrlei.litelog.printer

import com.aqrlei.litelog.LogLevel

/**
 * created by AqrLei on 2020/5/19
 */
interface ILogPrinter {

    fun print(level: Int, printerEntity: PrinterEntity)

    @LogLevel
    val level: Int
}