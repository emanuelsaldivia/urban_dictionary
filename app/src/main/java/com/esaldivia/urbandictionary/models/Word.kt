package com.esaldivia.urbandictionary.models

import com.google.gson.annotations.SerializedName

data class Word(@SerializedName("defid") val id: Int,
                @SerializedName("word") val word: String,
                @SerializedName("definition") val definition: String,
                @SerializedName("author") val author: String,
                @SerializedName("thumbs_up") val thumbsUp: Int,
                @SerializedName("thumbs_down") val thumbsDown: Int)