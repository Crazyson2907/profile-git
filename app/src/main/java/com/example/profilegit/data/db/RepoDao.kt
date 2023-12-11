package com.example.profilegit.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.profilegit.data.model.Repository

@Dao
interface RepoDao {
    @Query("SELECT * FROM repository")
    fun getRepos(): LiveData<List<Repository>>

    @Query("SELECT * FROM repository WHERE id = :id")
    suspend fun getRepoById(id: Int): Repository

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repo: Repository)

    @Update
    suspend fun updateRepo(updateRepo: Repository)

    @Delete
    suspend fun deleteRepo(repo: Repository)

    @Query("DELETE  FROM repository")
    suspend fun deleteRepos()
}