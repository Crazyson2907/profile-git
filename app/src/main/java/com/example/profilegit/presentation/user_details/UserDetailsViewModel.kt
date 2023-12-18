package com.example.profilegit.presentation.user_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profilegit.common.Constants
import com.example.profilegit.common.Status
import com.example.profilegit.domain.core.use_case.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableStateFlow<UserDetailState> =
        MutableStateFlow(UserDetailState.Loading)
    val state: StateFlow<UserDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_LOGIN)?.let { login ->
            getDetails(login)
        }
    }

    private fun getDetails(login: String) {
        viewModelScope.launch {
            getDetailsUseCase.execute(login)
                .onStart {
                    _state.value = UserDetailState.Loading
                }.collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _state.value = if (it.data != null) {
                                UserDetailState.DetailsSuccessfullyFetched(it.data)
                            } else {
                                UserDetailState.ErrorOccurred
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
}