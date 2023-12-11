package com.example.profilegit.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.profilegit.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(updateUser: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE  FROM user")
    suspend fun deleteUsers()
}