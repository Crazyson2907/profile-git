package com.example.profilegit.domain.repository

import com.example.profilegit.data.dto.DetailsDto
import com.example.profilegit.data.dto.UserDto

interface Repository {

    suspend fun getUsers(): List<UserDto>

    suspend fun getUserDetails(login: String): DetailsDto
}