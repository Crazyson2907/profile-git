package com.example.profilegit.presentation.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profilegit.domain.model.User
import com.example.profilegit.domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var _state = MutableStateFlow(emptyList<User>())
    val state: StateFlow<List<User>>
        get() = _state

    init {
        viewModelScope.launch {
            repository.getUsers()
        }
    }
}