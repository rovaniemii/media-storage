package com.hy0417sage.mediastorage.views

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.GsonBuilder
import com.hy0417sage.core.model.SearchItem
import org.json.JSONObject

class SharedPreference(context: Context) {
    private val pref = context.getSharedPreferences("storage", MODE_PRIVATE)
    private val editor = pref.edit()
    private var gson = GsonBuilder().create()

    fun getValue(key: String): SearchItem? {
        val value = pref.getString(key, null)
        return gson.fromJson(value, SearchItem::class.java)
    }

    fun setValue(key: String, value: SearchItem) {
        val data = gson.toJson(value)
        editor.putString(key, data).apply()
    }

    fun deleteValue(key: String) {
        editor.remove(key)
        editor.apply()
    }

    fun getAllValue(): MutableList<SearchItem> {
        var searchItemList: MutableList<SearchItem> = mutableListOf()
        pref.all.values.forEach { data ->
            val jsonObject = JSONObject(data.toString())
            val searchItem = SearchItem(
                imageUrl = jsonObject.getString("imageUrl"),
                datetime = jsonObject.getString("datetime"),
                bookmark = jsonObject.getBoolean("bookmark"),
                bookmarkTime = jsonObject.getString("bookmarkTime"),
            )
            searchItemList.add(searchItem)
        }
        return searchItemList
    }
}