package com.decagonhq.clads.data.local

import androidx.room.RoomDatabase

abstract class LocalDataSource : RoomDatabase() {

    // database is used get Dao to interact with entities
    abstract fun getClientDao() : ClientDao
    abstract fun getUserDao() : UserDao
}