package com.example.profilegit.data.network

import com.example.profilegit.data.dto.DetailsDto
import com.example.profilegit.data.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
    @GET("/users/{login}")
    suspend fun getUserDetails(@Path("login") login:String): DetailsDto
}