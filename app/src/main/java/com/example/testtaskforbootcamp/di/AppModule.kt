package com.example.testtaskforbootcamp.di

import android.app.Application
import dagger.Module
import dagger.Provides


@Module
class AppModule(private val app: Application) {
    @Provides
    @ApplicationScope
    fun provideApplication(): Application = app

}