package com.yyusufsefa.myapplication.db

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yyusufsefa.myapplication.model.Articles
import com.yyusufsefa.myapplication.model.Source


object TypeConverter {

    @androidx.room.TypeConverter
    @JvmStatic
    fun articleToString(array: List<Articles>): String {
        return if (array.isEmpty()) {
            ""
        } else {
            Gson().toJson(array)
        }
    }

    @androidx.room.TypeConverter
    @JvmStatic
    fun stringToArticle(value: String): List<Articles> {
        val listType = object : TypeToken<List<Articles>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @androidx.room.TypeConverter
    @JvmStatic
    fun stringToSource(value: String): Source? {
        return if (value == "") {
            null
        } else {
            val listType = object : TypeToken<Source>() {}.type
            Gson().fromJson(value, listType)
        }
    }

    @androidx.room.TypeConverter
    @JvmStatic
    fun sourceToString(value: Source): String {
        return run {
            Gson().toJson(value)
        }
    }
}