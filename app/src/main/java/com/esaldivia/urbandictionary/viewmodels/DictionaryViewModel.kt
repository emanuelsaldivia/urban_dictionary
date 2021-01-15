package com.esaldivia.urbandictionary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esaldivia.urbandictionary.models.Word
import com.esaldivia.urbandictionary.repositories.WordRepository
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(val repository: WordRepository) : ViewModel() {

    private val _wordDefinitionsLiveData: MutableLiveData<ArrayList<Word>> = MutableLiveData()
    val wordDefinitionsLiveData: LiveData<ArrayList<Word>> = _wordDefinitionsLiveData

}