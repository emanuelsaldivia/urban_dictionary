package com.esaldivia.urbandictionary.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.esaldivia.urbandictionary.models.Word
import com.esaldivia.urbandictionary.repositories.WordRepository
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(private val repository: WordRepository, application: Application) : AndroidViewModel(application) {

    private val _isOrderedByThumbsUp: MutableLiveData<Boolean> = MutableLiveData()
    val isOrderedByThumbsUp: LiveData<Boolean> = _isOrderedByThumbsUp

    private val _definitionsOrder: MutableLiveData<ArrayList<Word>> = MutableLiveData()
    val definitionsOrder: LiveData<ArrayList<Word>> = _definitionsOrder


    fun searchTerm(word: String) = repository.handleSearch(word, getApplication<Application>().applicationContext)

    fun orderByThumbsUp(definitions: ArrayList<Word>) {
        definitions.sortByDescending { it.thumbsUp }
        _definitionsOrder.value = definitions
        _isOrderedByThumbsUp.value = true
    }

    fun orderByThumbsDown(definitions: ArrayList<Word>) {
        definitions.sortByDescending { it.thumbsDown }
        _definitionsOrder.value = definitions
        _isOrderedByThumbsUp.value = false

    }

}