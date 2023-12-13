package com.example.profilegit.presentation.user_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profilegit.common.Resource
import com.example.profilegit.common.Constants
import com.example.profilegit.domain.use_case.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(UserDetailState())
    val state: State<UserDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_LOGIN)?.let { login ->
            getDetails(login)
        }
    }

    private fun getDetails(login: String) {
        getDetailsUseCase(login).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UserDetailState(user = result.data)
                }
                is Resource.Error -> {
                    _state.value = UserDetailState(error = result.message ?:  "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = UserDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}