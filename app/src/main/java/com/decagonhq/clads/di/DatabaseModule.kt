package com.decagonhq.clads.di

import android.content.Context
import androidx.room.Room
import com.decagonhq.clads.data.local.LocalDataSource
import com.decagonhq.clads.data.remote.RemoteDataSourceApi
import com.decagonhq.clads.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext appContext: Context): LocalDataSource {
            // build database with 3 parameters. context, database class and a name
            return Room.databaseBuilder(
                appContext,
                LocalDataSource::class.java,
                "cladsDatabase"
            ).build()
        }

        @Singleton
        @Provides
        fun provideClientDao(database: LocalDataSource) = database.getClientDao()

        @Singleton
        @Provides
        fun provideUserDao(database: LocalDataSource) = database.getUserDao()

        @Singleton
        @Provides
        fun provideRepository(
            remoteDataSource: RemoteDataSourceApi,
            localDataSource: LocalDataSource
        ) = Repository(remoteDataSource, localDataSource)
    }