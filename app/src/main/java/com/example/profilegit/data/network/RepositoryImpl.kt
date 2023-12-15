package com.example.profilegit.data.network

import com.example.profilegit.common.Resource
import com.example.profilegit.data.dto.toDetails
import com.example.profilegit.data.dto.toUser
import com.example.profilegit.domain.core.model.Details
import com.example.profilegit.domain.core.model.User
import com.example.profilegit.domain.network.ApiService
import com.example.profilegit.domain.network.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ApiService,
) : Repository {
    override suspend fun getUsers(): Flow<Resource<List<User>>> {
        return flow {

            try {
                val result = api.getUsers()
                if (result.isNotEmpty()) {
                    emit(Resource.success(result.map {
                        it.toUser()
                    }))
                } else {
                    emit(Resource.error("No users found", null))
                }
            } catch (e: Exception) {
                emit(Resource.error("Failed to fetch users: ${e.message}", null))
            }
        }
    }

    override suspend fun getUserDetails(login: String): Flow<Resource<Details>> {
        return flow {
            try {
                val result = api.getUserDetails(login)
                emit(Resource.success(result.toDetails()))
            } catch (e: Exception) {
                emit(Resource.error("Failed to fetch user details: ${e.message}", null))
            }
        }
    }
}
