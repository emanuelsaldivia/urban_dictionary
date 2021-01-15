package com.esaldivia.urbandictionary.di.modules

import com.esaldivia.urbandictionary.network.DictionaryApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DictionaryModule {
    companion object {
        @Provides
        fun provideSearchItemApi(retrofit: Retrofit): DictionaryApi {
            return retrofit.create(DictionaryApi::class.java)
        }
    }
}