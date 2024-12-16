package com.example.microblog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.microblog.network.ApiService
import com.example.microblog.ui.presentation.screens.homescreen.HomeScreen
import com.example.microblog.ui.presentation.screens.homescreen.HomeViewModel
import com.example.microblog.ui.theme.MicroblogTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MicroblogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        val homeViewModel = hiltViewModel<HomeViewModel>()
                    HomeScreen(modifier = Modifier, viewModel = homeViewModel)
                }
            }
        }

    }
}


