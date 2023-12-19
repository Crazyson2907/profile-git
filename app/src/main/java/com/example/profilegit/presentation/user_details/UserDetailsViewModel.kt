package com.example.profilegit.presentation.user_details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profilegit.common.Constants
import com.example.profilegit.common.Status
import com.example.profilegit.domain.cache.usecase.SaveUserDetailsToCacheUseCase
import com.example.profilegit.domain.cache.usecase.SaveUsersToCacheUseCase
import com.example.profilegit.domain.core.use_case.GetDetailsUseCase
import com.example.profilegit.domain.core.use_case.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase,
    private val saveDetailsUseCase: SaveUserDetailsToCacheUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val saveUsersToCacheUseCase: SaveUsersToCacheUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailState: MutableStateFlow<UserDetailState> =
        MutableStateFlow(UserDetailState.Loading)
    val state: StateFlow<UserDetailState> = _detailState

    private val _userState: MutableStateFlow<UserDetailState> =
        MutableStateFlow(UserDetailState.Loading)
    val userState: StateFlow<UserDetailState> = _userState

    init {
        savedStateHandle.get<String>(Constants.PARAM_LOGIN)?.let { login ->
            getDetails(login)
        }
    }

    private fun getDetails(login: String) {
        viewModelScope.launch {
            getDetailsUseCase.execute(login)
                .onStart {
                    _detailState.value = UserDetailState.Loading
                }.collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            it.data?.let { details ->
                                _detailState.value =
                                    UserDetailState.DetailsSuccessfullyFetched(details)
                                fetchUserData(details.id)
                            } ?: run {
                                _detailState.value = UserDetailState.ErrorOccurred
                            }
                        }

                        Status.ERROR -> {
                            UserDetailState.ErrorOccurred
                        }

                        Status.LOADING -> {
                            UserDetailState.Loading
                        }
                    }
                }
        }
    }

    private fun fetchUserData(id: Int) {
        viewModelScope.launch {
            getUsersUseCase.executeSingle(id)
                .onStart {
                    _userState.value = UserDetailState.Loading
                }.collect {
                    _userState.value = it.data?.let { user ->
                        UserDetailState.UserSuccessfullyFetched(user)
                    } ?: UserDetailState.ErrorOccurred
                }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            val currentDetails =
                (_detailState.value as? UserDetailState.DetailsSuccessfullyFetched)?.details
            val updatedDetails = currentDetails?.copy(isFavorite = !(currentDetails.isFavorite)!!)

            val currentUser = (_userState.value as? UserDetailState.UserSuccessfullyFetched)?.user
            val updatedUser = currentUser?.copy(isFavorite = updatedDetails?.isFavorite ?: false)

            updatedDetails?.let {
                _detailState.value = UserDetailState.DetailsSuccessfullyFetched(it)
                saveDetailsUseCase.execute(it)
                Log.d("ViewModel", "Favorite Toggled on Details: ${updatedDetails.isFavorite}")
            }
            updatedUser?.let {
                _userState.value = UserDetailState.UserSuccessfullyFetched(it)
                saveUsersToCacheUseCase.executeSingle(it)
                Log.d("ViewModel", "User added to favorite: ${updatedUser.isFavorite}")
            }
        }
    }
}