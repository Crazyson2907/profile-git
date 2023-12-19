package com.example.profilegit.presentation.user_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profilegit.common.Status
import com.example.profilegit.domain.cache.usecase.SaveUsersToCacheUseCase
import com.example.profilegit.domain.core.model.User
import com.example.profilegit.domain.core.use_case.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val saveUsersToCacheUseCase: SaveUsersToCacheUseCase
) : ViewModel() {

    private var originalList = listOf<User>()
    private var isSorted = false

    private val _state = MutableStateFlow<UserListState>(UserListState.Loading)
    val state: StateFlow<UserListState> = _state

    init {
        getUsers()
        updateListState()
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase.execute()
                .onStart {
                    _state.value = UserListState.Loading
                }.collect { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            originalList = resource.data ?: listOf()
                            updateListState()
                        }

                        Status.ERROR -> {
                            _state.value = UserListState.ErrorOccurred
                        }

                        Status.LOADING -> {
                        }
                    }
                }
        }
    }

    private fun updateListState() {
        val listToShow = if (isSorted) {
            originalList.sortedBy { it.login }
        } else {
            originalList
        }
        _state.value = UserListState.ListSuccessfullyFetched(listToShow)
    }

    fun toggleFavorite(user: User) {
        viewModelScope.launch {
            val users = (_state.value as? UserListState.ListSuccessfullyFetched)?.list
            val updatedUsers = users?.map {
                if (it.id == user.id) it.copy(isFavorite = !it.isFavorite) else it
            }

            _state.value = UserListState.ListSuccessfullyFetched(updatedUsers!!)

            updatedUsers.find { it.id == user.id }?.let { updatedUser ->
                saveUsersToCacheUseCase.executeSingle(updatedUser)
                Log.d("ViewModel", "User added to favorite: ${updatedUser.isFavorite}")
            }
        }
    }
}