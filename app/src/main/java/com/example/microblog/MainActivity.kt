package com.example.microblog

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.microblog.network.ApiService
import com.example.microblog.ui.theme.MicroblogTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

                }
            }
        }
        //Test the api connection

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getUser(1)
                if (response.isSuccessful) {
                    Log.d("NetworkTest", "Success: ${response.body()}")
                } else {
                    Log.e("NetworkTest", "Error: ${response.errorBody()?.string()}")
                }
            }catch ( e: Exception ){
                Log.e("NetworkTest", "Exception: ${e.message}")
            }
        }
    }
}


