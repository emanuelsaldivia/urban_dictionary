package com.esaldivia.urbandictionary.di.modules

import androidx.lifecycle.ViewModel
import com.esaldivia.urbandictionary.di.ViewModelKey
import com.esaldivia.urbandictionary.viewmodels.DictionaryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DictionaryViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DictionaryViewModel::class)
    abstract fun bindDictionaryViewModel( dictionaryViewModel: DictionaryViewModel): ViewModel
}