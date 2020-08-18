package com.aqrlei.litelog.config

import com.aqrlei.litelog.LogLevel

/**
 * created by AqrLei on 2020/5/19
 */
interface ILogPrinterConfig {

    @LogLevel
    var level: Int
}