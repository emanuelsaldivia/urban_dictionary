package com.esaldivia.urbandictionary.repositories

import android.content.Context
import androidx.lifecycle.liveData
import com.esaldivia.urbandictionary.R
import com.esaldivia.urbandictionary.network.DictionaryApi
import com.esaldivia.urbandictionary.network.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

open class WordRepository @Inject constructor(private val dictionaryApi: DictionaryApi){

    suspend fun searchTerm(word: String) = dictionaryApi.searchWord(word)

    open fun handleSearch(word: String, context: Context) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.succces(data = searchTerm(word)))
        } catch (exception: Exception) {
            val context = context.applicationContext
            emit(Resource.error(null, exception.message ?: context.getString(R.string.unexpected_error)))
        }
    }
}