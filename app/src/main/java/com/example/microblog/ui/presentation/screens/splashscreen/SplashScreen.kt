package com.example.microblog.ui.presentation.screens.splashscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel : SplashViewModel,
    navController: NavController
){
    val hasToken by viewModel.hasToken.collectAsState()
    LaunchedEffect(hasToken){
        delay(2000)
        if (hasToken) {
            navController.navigate("homeScreen")
        } else {
            navController.navigate("loginScreen")
        }
    }
}