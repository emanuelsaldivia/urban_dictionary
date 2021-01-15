package com.esaldivia.urbandictionary.viewmodels

import androidx.lifecycle.ViewModel
import com.esaldivia.urbandictionary.repositories.WordRepository
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(val repository: WordRepository) : ViewModel() {

}