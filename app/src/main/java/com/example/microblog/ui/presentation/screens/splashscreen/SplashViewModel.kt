package com.example.microblog.ui.presentation.screens.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microblog.data.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authRepositoryImpl: AuthRepositoryImpl)  : ViewModel() {
    private val _hasToken : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val hasToken : StateFlow<Boolean>  = _hasToken.asStateFlow()

    init {
        viewModelScope.launch {
                _hasToken.value = authRepositoryImpl.getToken() != null
                println(_hasToken.value.toString())
            }
        }
    }