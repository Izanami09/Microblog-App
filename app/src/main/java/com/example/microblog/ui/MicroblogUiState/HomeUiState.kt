package com.example.microblog.ui.MicroblogUiState

import com.example.microblog.models.User

data class HomeUiState(
    val isLoading : Boolean = false,
    val users: List<User> = emptyList(),
    val errorMessage: String? = null,


)
