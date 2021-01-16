package com.esaldivia.urbandictionary.network

import com.esaldivia.urbandictionary.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DictionaryApi {

    @GET("define")
    @Headers("x-rapidapi-key: 1fea65780emsh67628a4feba52e4p1f3571jsn74b274e66ac0",
        "x-rapidapi-host: mashape-community-urban-dictionary.p.rapidapi.com",
        "useQueryString: true")
    suspend fun searchWord(@Query("term") word: String): SearchResponse

}