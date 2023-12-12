package com.example.profilegit.domain.use_case

import com.example.profilegit.common.Resource
import com.example.profilegit.data.dto.toUser
import com.example.profilegit.domain.model.User
import com.example.profilegit.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading())
            val users = repository.getUsers().map { it.toUser() }
            emit(Resource.Success(users))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}
