package br.com.dbc.application.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {
    fun formatDate(date: Date, pattern:String) = SimpleDateFormat(pattern).format(date)


    fun formatDate(date: Long, pattern: String) = formatDate(Date(date),pattern)

}