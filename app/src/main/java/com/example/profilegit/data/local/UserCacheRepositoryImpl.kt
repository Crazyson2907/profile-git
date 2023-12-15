package com.example.profilegit.data.local

import com.example.profilegit.di.IoDispatcher
import com.example.profilegit.domain.cache.core.UserCacheRepository
import com.example.profilegit.domain.core.model.Details
import com.example.profilegit.domain.core.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class UserCacheRepositoryImpl(
    private val dao: GitUsersDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : UserCacheRepository {
    override suspend fun getUsersList(): List<User> = coroutineScope {
        withContext(ioDispatcher) {
            dao.getUsers().map { it.toUser() }
        }
    }

    override suspend fun saveUsersToCache(users: List<User>) {
        withContext(ioDispatcher) {
            dao.insertUsers(users = users.map { it.toUserEntity() })
        }
    }

    override suspend fun getDetails(login: String): Details = coroutineScope {
        withContext(ioDispatcher) {
            dao.getUserDetails(login).toDetails()
        }
    }

    override suspend fun saveDetailsToCache(details: Details) {
        withContext(ioDispatcher) {
            dao.insertDetails(details = details.toDetailsEntity())
        }
    }

}