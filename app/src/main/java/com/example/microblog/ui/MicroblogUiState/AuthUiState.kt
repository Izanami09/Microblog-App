package com.example.microblog.ui.MicroblogUiState

data class AuthUiState(
    var loading : Boolean = false,
    var success : Boolean = false,
    var error : Throwable? = null

)