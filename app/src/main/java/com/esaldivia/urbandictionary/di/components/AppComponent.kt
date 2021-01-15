package com.esaldivia.urbandictionary.di.components

import android.app.Application
import com.esaldivia.urbandictionary.BaseApplication
import com.esaldivia.urbandictionary.di.modules.ActivityBuildersModule
import com.esaldivia.urbandictionary.di.modules.AppModule
import com.esaldivia.urbandictionary.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class,
    ViewModelFactoryModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}