package com.decagonhq.clads.di

import android.content.Context
import android.content.SharedPreferences
import com.decagonhq.clads.data.local.AppSharedPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {
    @Singleton
    @Provides
    fun providesSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesAppSharedPreference(sharedPreferences: SharedPreferences): AppSharedPreference {
        return AppSharedPreference(sharedPreferences)
    }
}
