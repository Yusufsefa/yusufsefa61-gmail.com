package com.yyusufsefa.myapplication.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "head_lines_table")
data class HeadLines(

    @SerializedName("status")
    val status: String?,

    @SerializedName("totalResults")
    val totalResults: String?,

    @SerializedName("articles")
    val articles: List<Articles>?
)