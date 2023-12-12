package com.example.profilegit.data.repository

import com.example.profilegit.data.dto.DetailsDto
import com.example.profilegit.data.dto.UserDto
import com.example.profilegit.data.network.ApiService
import com.example.profilegit.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ApiService
) : Repository {
    override suspend fun getUsers(): List<UserDto> {
        return api.getUsers()
    }

    override suspend fun getUserDetails(login: String): DetailsDto {
        return api.getUserDetails(login)
    }

}