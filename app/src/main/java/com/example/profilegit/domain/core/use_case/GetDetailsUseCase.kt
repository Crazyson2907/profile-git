package com.example.profilegit.domain.core.use_case

import com.example.profilegit.common.Resource
import com.example.profilegit.domain.cache.usecase.GetUserDetailsFromCacheUseCase
import com.example.profilegit.domain.core.model.Details
import com.example.profilegit.domain.network.use_case.FetchUserDetailsFromApiUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Presentation use case
 */
interface GetDetailsUseCase {
    suspend fun execute(login: String): Flow<Resource<Details>>

    class Base(
        private val fetchUserDetailsFromApiUseCase: FetchUserDetailsFromApiUseCase,
        private val getUserDetailsFromCacheUseCase: GetUserDetailsFromCacheUseCase
    ) : GetDetailsUseCase {
        override suspend fun execute(login: String): Flow<Resource<Details>> = flow {

            val cachedDetails =  getUserDetailsFromCacheUseCase.execute(login)
            if (cachedDetails != null) {
                emit(Resource.success(cachedDetails))
            } else {
                fetchUserDetailsFromApiUseCase.execute(login)
                    .collect { apiResult ->
                        emit(apiResult)
                    }
            }
        }

    }
}