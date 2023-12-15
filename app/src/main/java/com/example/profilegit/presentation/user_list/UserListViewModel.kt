package com.example.profilegit.presentation.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profilegit.common.Status
import com.example.profilegit.domain.core.use_case.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<UserListState> =
        MutableStateFlow(UserListState.Loading)
    val state: StateFlow<UserListState> = _state

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase.execute()
                .onStart {
                    _state.value = UserListState.Loading
                }.collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _state.value = if (it.data != null) {
                                UserListState.ListSuccessfullyFetched(it.data)
                            } else {
                                UserListState.ErrorOccurred
                            }
                        }

                        Status.ERROR -> {
                            UserListState.ErrorOccurred
                        }

                        Status.LOADING -> {
                            UserListState.Loading
                        }
                    }
                }
        }
    }
}