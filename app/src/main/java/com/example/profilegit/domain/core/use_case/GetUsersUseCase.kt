package com.example.profilegit.domain.core.use_case

import com.example.profilegit.common.Resource
import com.example.profilegit.domain.cache.usecase.GetUsersFromCacheUseCase
import com.example.profilegit.domain.core.model.User
import com.example.profilegit.domain.network.use_case.FetchUserListFromApiUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetUsersUseCase {
    suspend fun execute(): Flow<Resource<List<User>>>

    class Base(
        private val usersFromApiUseCase: FetchUserListFromApiUseCase,
        private val getUsersFromCacheUseCase: GetUsersFromCacheUseCase
    ) : GetUsersUseCase {
        override suspend fun execute(): Flow<Resource<List<User>>> {
            getUsersFromCacheUseCase.execute().let {
                if (it.isEmpty()) {
                    return usersFromApiUseCase.execute()
                } else {
                    return flow { emit(Resource.success(it)) }
                }
            }
        }

    }
}
