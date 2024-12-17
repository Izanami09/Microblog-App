package com.example.microblog.ui.presentation.screens.authscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microblog.data.AuthRepositoryImpl
import com.example.microblog.ui.MicroblogUiState.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl
): ViewModel() {
    private  val _authUiState : MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState())
    val authState = _authUiState.asStateFlow()


    fun login(username : String, password:String) {
        viewModelScope.launch {
            val response = authRepositoryImpl.login(username, password)
            if(response.isSuccess) {
                authRepositoryImpl.saveToken(response.getOrNull()!!.token)
            }
            _authUiState.update {
                if (response.isSuccess){
                    it.copy(loading = false,success = true)
                }else{
                    it.copy(loading = false, error = response.exceptionOrNull())
                }
            }
            println(authRepositoryImpl.getToken())
        }
    }


}