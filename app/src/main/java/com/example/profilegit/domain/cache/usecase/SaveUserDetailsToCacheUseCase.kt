package com.example.profilegit.domain.cache.usecase

import com.example.profilegit.domain.cache.core.UserCacheRepository
import com.example.profilegit.domain.core.model.Details

interface SaveUserDetailsToCacheUseCase {

    suspend fun execute(details: Details)

    class Base(private val repository: UserCacheRepository) : SaveUserDetailsToCacheUseCase {
        override suspend fun execute(details: Details) {
            return repository.saveDetailsToCache(details)
        }

    }
}