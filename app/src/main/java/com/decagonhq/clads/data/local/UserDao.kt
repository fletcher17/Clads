package com.decagonhq.clads.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.decagonhq.clads.data.entity.Post

@Dao
interface UserDao {

    suspend fun getPost(): LiveData<List<Post>>

    suspend fun savePost()
}
