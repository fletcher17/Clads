package com.decagonhq.clads.di

import com.decagonhq.clads.data.remote.RemoteDataSourceApi
import com.decagonhq.clads.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSourceApi
    ) = Repository(remoteDataSource)
}
