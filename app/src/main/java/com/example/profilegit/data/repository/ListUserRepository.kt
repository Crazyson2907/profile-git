package com.example.profilegit.data.repository

import androidx.lifecycle.LiveData
import com.example.profilegit.data.db.UserDao
import com.example.profilegit.data.model.User
import com.example.profilegit.network.ApiService
import javax.inject.Inject

class ListUserRepository @Inject constructor(private val apiGithubService: ApiService, private val userDao: UserDao) {

    suspend fun loadListUser(){
        apiGithubService.getUsers().map {
            userDao.insertUser(it)
        }
    }

    fun getUserFromCache() : LiveData<List<User>> {
        return userDao.getUsers()
    }
}