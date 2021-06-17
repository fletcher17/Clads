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

    // dependency injection for database tp be uncommented when we need it.

//        @Singleton
//        @Provides
//        fun provideDatabase(@ApplicationContext appContext: Context): LocalDataSource {
//            // build database with 3 parameters. context, database class and a name
//            return Room.databaseBuilder(
//                appContext,
//                LocalDataSource::class.java,
//                "cladsDatabase"
//            ).build()
//        }

//        @Singleton
//        @Provides
//        fun provideClientDao(database: LocalDataSource) = database.getClientDao()
//
//        @Singleton
//        @Provides
//        fun provideUserDao(database: LocalDataSource) = database.getUserDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSourceApi,
        // localDataSource: LocalDataSource
    ) = Repository(remoteDataSource)
}
