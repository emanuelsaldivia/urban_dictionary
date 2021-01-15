package com.esaldivia.urbandictionary.di.modules

import androidx.lifecycle.ViewModelProvider
import com.esaldivia.urbandictionary.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory)
            : ViewModelProvider.Factory
}