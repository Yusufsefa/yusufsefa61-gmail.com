package com.yyusufsefa.myapplication.model

import com.google.gson.annotations.SerializedName

data class HeadLines(


    @SerializedName("status")
    val status:String?,

    @SerializedName("totalResults")
    val totalResults:String?,

    @SerializedName("articles")
    val articles:List<Articles>?
){




}