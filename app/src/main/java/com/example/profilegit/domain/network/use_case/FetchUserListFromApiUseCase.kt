package com.example.profilegit.domain.network.use_case

import com.example.profilegit.common.Resource
import com.example.profilegit.domain.cache.usecase.SaveUsersToCacheUseCase
import com.example.profilegit.domain.core.model.User
import com.example.profilegit.domain.network.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

interface FetchUserListFromApiUseCase {

    suspend fun execute(): Flow<Resource<List<User>>>

    class Base(
        private val repository: Repository,
        private val saveUsersToCacheUseCase: SaveUsersToCacheUseCase
    ) : FetchUserListFromApiUseCase {
        override suspend fun execute(): Flow<Resource<List<User>>> {
            return repository.getUsers().onEach {
                it.data?.let { users ->
                    saveUsersToCacheUseCase.execute(users)
                }
            }
        }

    }
}