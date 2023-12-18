package com.example.profilegit.presentation.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profilegit.common.Status
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
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private var originalList = listOf<User>()
    private var isSorted = false

    private val _state = MutableStateFlow<UserListState>(UserListState.Loading)
    val state: StateFlow<UserListState> = _state

    init {
        getUsers()
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

    fun toggleSort() {
        isSorted = !isSorted
        updateListState()
    }

    private fun updateListState() {
        val listToShow = if (isSorted) {
            originalList.sortedBy { it.login }
        } else {
            originalList
        }
        _state.value = UserListState.ListSuccessfullyFetched(listToShow)
    }
}