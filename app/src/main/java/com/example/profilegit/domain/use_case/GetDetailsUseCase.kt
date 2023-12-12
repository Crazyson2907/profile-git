package com.example.profilegit.domain.use_case

import com.example.profilegit.common.Resource
import com.example.profilegit.data.dto.toDetails
import com.example.profilegit.domain.model.Details
import com.example.profilegit.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(login: String): Flow<Resource<Details>> = flow {
        try {
            emit(Resource.Loading())
            val details = repository.getUserDetails(login).toDetails()
            emit(Resource.Success(details))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}