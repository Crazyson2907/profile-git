package com.example.profilegit.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.profilegit.data.dto.DetailsDto
import com.example.profilegit.data.dto.UserDto
import com.example.profilegit.domain.cache.entity.DetailsEntity
import com.example.profilegit.domain.cache.entity.UserEntity
import com.example.profilegit.domain.core.model.Details
import com.example.profilegit.domain.core.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface GitUsersDao {

    @Query("SELECT * FROM user_entity")
    fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM details_entity WHERE login LIKE :login")
    fun getUserDetails(login: String): DetailsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(details: DetailsEntity)
}