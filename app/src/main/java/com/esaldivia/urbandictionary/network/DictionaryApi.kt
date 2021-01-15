package com.esaldivia.urbandictionary.network

import com.esaldivia.urbandictionary.models.SearchResponse
import retrofit2.http.GET

interface DictionaryApi {

    @GET
    suspend fun searchWord(word: String): SearchResponse

}