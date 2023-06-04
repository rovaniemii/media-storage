package com.hy0417sage.mediastorage.views

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.GsonBuilder
import com.hy0417sage.domain.model.ViewData
import org.json.JSONObject

class SharedPreference(context: Context) {
    private val pref = context.getSharedPreferences("storage", MODE_PRIVATE)
    private val editor = pref.edit()
    private var gson = GsonBuilder().create()

    fun getValue(key: String): ViewData? {
        val value = pref.getString(key, null)
        return gson.fromJson(value, ViewData::class.java)
    }

    fun setValue(key: String, value: ViewData) {
        val data = gson.toJson(value)
        editor.putString(key, data).apply()
    }

    fun deleteValue(key: String) {
        editor.remove(key)
        editor.apply()
    }

    fun getAllValue(): MutableList<ViewData> {
        var viewDataList: MutableList<ViewData> = mutableListOf()
        pref.all.values.forEach { data ->
            val jsonObject = JSONObject(data.toString())
            val viewData = ViewData(
                thumbnail = jsonObject.getString("thumbnail"),
                datetime = jsonObject.getString("datetime"),
                like = jsonObject.getBoolean("like"),
                saveTime = jsonObject.getString("saveTime"),
                source = jsonObject.getString("source"),
            )
            viewDataList.add(viewData)
        }
        return viewDataList
    }
}