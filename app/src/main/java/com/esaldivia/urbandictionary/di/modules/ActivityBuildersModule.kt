package com.esaldivia.urbandictionary.di.modules

import com.esaldivia.urbandictionary.ui.DicitionaryActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [
        DictionaryViewModelModule::class,
        DictionaryModule::class
    ])
    abstract fun contributeDictionaryActivity(): DicitionaryActivity

}