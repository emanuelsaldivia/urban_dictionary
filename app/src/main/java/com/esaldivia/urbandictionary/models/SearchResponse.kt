package com.esaldivia.urbandictionary.models

import com.google.gson.annotations.SerializedName

data class SearchResponse(@SerializedName("list") val definitions: ArrayList<Word>)