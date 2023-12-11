package com.example.profilegit.network

import com.example.profilegit.data.model.Repository
import com.example.profilegit.data.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
    @GET("/users/{login}/repos")
    suspend fun getRepos(@Path("login") login:String): List<Repository>


    companion object {
        private var api: ApiService? = null
        private const val BASE_URL: String = "https://api.github.com/"


        fun getInstance(): ApiService {
            if (api == null) {
                api = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    // .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return api!!
        }
    }
}