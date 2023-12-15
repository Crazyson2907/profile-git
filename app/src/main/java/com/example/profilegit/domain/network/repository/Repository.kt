package com.example.profilegit.domain.network.repository

import com.example.profilegit.common.Resource
import com.example.profilegit.domain.core.model.Details
import com.example.profilegit.domain.core.model.User
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getUsers(): Flow<Resource<List<User>>>

    suspend fun getUserDetails(login: String): Flow<Resource<Details>>
}