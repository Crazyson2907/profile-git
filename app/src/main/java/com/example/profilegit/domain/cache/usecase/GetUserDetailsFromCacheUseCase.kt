package com.example.profilegit.domain.cache.usecase

import com.example.profilegit.domain.cache.core.UserCacheRepository
import com.example.profilegit.domain.core.model.Details

interface GetUserDetailsFromCacheUseCase {

    suspend fun execute(login: String): Details?

    class Base(private val repository: UserCacheRepository) : GetUserDetailsFromCacheUseCase {
        override suspend fun execute(login: String): Details? {
            return repository.getDetails(login)
        }

    }
}