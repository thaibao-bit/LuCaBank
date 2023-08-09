package com.example.lucafate.di

import com.example.lucafate.storage.SharedPreferencesStorage
import com.example.lucafate.storage.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}