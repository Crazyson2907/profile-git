package com.example.profilegit.di

import com.example.profilegit.Application
import com.example.profilegit.common.Constants
import com.example.profilegit.data.local.GitUsersDatabase
import com.example.profilegit.domain.network.ApiService
import com.example.profilegit.data.network.RepositoryImpl
import com.example.profilegit.domain.network.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}