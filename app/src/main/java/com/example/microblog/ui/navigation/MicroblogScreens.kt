package com.example.microblog.ui.navigation

sealed class MicroblogScreen(val route: String){
   data object Home : MicroblogScreen("homeScreen")
    data object Login : MicroblogScreen("loginScreen")
    data object Register : MicroblogScreen("registerScreen")

    data object Splash : MicroblogScreen(route = "splashScreen")

}