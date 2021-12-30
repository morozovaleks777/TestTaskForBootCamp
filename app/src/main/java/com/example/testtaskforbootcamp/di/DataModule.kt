package com.example.testtaskforbootcamp.di

import android.app.Application
import com.example.testtaskforbootcamp.data.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @ApplicationScope
    fun provideApplicationDatabase(app: Application) =
        AppDatabase.getInstance(app)

    @Provides
    @ApplicationScope
    fun provideUserDao(db: AppDatabase) = db.wordListDao()


}