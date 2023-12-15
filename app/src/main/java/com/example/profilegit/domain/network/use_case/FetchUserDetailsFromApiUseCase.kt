package com.example.profilegit.domain.network.use_case

import com.example.profilegit.common.Resource
import com.example.profilegit.domain.cache.usecase.SaveUserDetailsToCacheUseCase
import com.example.profilegit.domain.core.model.Details
import com.example.profilegit.domain.network.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

interface FetchUserDetailsFromApiUseCase {

    suspend fun execute(login: String): Flow<Resource<Details>>

    class Base(
        private val repository: Repository,
        private val detailsToCacheUseCase: SaveUserDetailsToCacheUseCase
    ) : FetchUserDetailsFromApiUseCase {
        override suspend fun execute(login: String): Flow<Resource<Details>> =
            repository.getUserDetails(login).onEach {
                it.data?.let { details ->
                    detailsToCacheUseCase.execute(details)
                }
            }

    }
}