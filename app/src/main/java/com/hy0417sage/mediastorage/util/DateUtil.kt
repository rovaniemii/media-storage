package com.hy0417sage.mediastorage.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    @SuppressLint("SimpleDateFormat")
    fun dateAndTime(): String {
        val now = System.currentTimeMillis()
        val date = Date(now)
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
        return simpleDateFormat.format(date)
    }
}