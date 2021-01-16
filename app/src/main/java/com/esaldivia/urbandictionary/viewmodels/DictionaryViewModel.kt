package com.esaldivia.urbandictionary.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esaldivia.urbandictionary.models.Word
import com.esaldivia.urbandictionary.repositories.WordRepository
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(val repository: WordRepository, application: Application) : AndroidViewModel(application) {

    fun searchTerm(word: String) = repository.handleSearch(word, getApplication<Application>().applicationContext)

}