package com.example.microblog.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.microblog.ui.presentation.screens.authscreen.LoginScreen
import com.example.microblog.ui.presentation.screens.homescreen.HomeScreen
import com.example.microblog.ui.presentation.screens.splashscreen.SplashScreen


@Composable
fun MicroblogNavigation(){
    val navController : NavHostController  = rememberNavController()


    NavHost(navController = navController, startDestination = MicroblogScreen.Splash.route){
        composable(MicroblogScreen.Login.route){ LoginScreen(viewModel = hiltViewModel(), navController = navController )}
        composable(MicroblogScreen.Splash.route) { SplashScreen(navController = navController, viewModel = hiltViewModel())}
        composable(MicroblogScreen.Home.route){ HomeScreen(modifier = Modifier, viewModel = hiltViewModel())}
    }
}