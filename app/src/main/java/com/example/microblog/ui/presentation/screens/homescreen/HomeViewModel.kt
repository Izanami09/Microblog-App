package com.example.microblog.ui.presentation.screens.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microblog.data.UserRepositoryImpl
import com.example.microblog.ui.MicroblogUiState.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class HomeViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel(){
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState : StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    private var hasLoaded = false

    init {
        if (!hasLoaded){
            loadUsers()
        }

    }

   private fun loadUsers() {
        viewModelScope.launch {
            val result = userRepositoryImpl.getUsers()
            _homeUiState.update {
                if (result.isSuccess){
                    it.copy(isLoading = false, users = result.getOrNull() ?: emptyList() )
                }
                else{
                    it.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message ?: "Unknown error occured")
                }
            }
            hasLoaded = true
        }
    }


     fun logout(){
        viewModelScope.launch {
            userRepositoryImpl.toDeleteToken()
        }
    }

    fun retry(){
        viewModelScope.launch {
            loadUsers()
        }
    }


}