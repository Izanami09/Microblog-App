package com.example.microblog.ui.presentation.screens.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.microblog.models.Links
import com.example.microblog.models.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier, viewModel: HomeViewModel, navController: NavController){

    val homeUiState by viewModel.homeUiState.collectAsState()

    //Creating Bottom Aligned App Bar
    Scaffold(
        topBar = {CenterAlignedTopAppBar(title = { Text(text = "Microblog")}, actions = {
            Button(onClick = {
                viewModel.logout()
                navController.navigate("loginScreen")
            }) {
                Text(text = "Logout")
            }
        })  },
        modifier = modifier
    ){
        topBarPaddingValue ->
        if (homeUiState.isLoading){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(text = "Content is loading")

            }

        }
        else{
            if(homeUiState.users.isEmpty())
            {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(topBarPaddingValue)
                ) {
                    Text(text = "No user data found")
                    Button(onClick = { viewModel.retry() }) {
                       Text(text = "Retry")
                    }

                }
            }
            else {
                Column(
                    Modifier
                        .padding(topBarPaddingValue)
                        .fillMaxSize()
                        .padding(4.dp)
                ) {
                    BlogFeed(
                        modifier = Modifier,
                        userList = homeUiState.users
                    )
                }
               
            }
        }

    }
        
}

@Composable
fun BlogContainer(modifier: Modifier, user: User){
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(4.dp)
        .background(color = Color.Transparent)
    ){
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(60.dp),
            ) {
                Text(text = user.username.toString() ?: "No username", modifier= modifier
                    .padding(4.dp)
                    .fillMaxWidth())
            }
            println(user)
            Text(text = user.username ?: "It is null")
            Text(text = "This is the blog")
        }
    }
}

@Composable
fun BlogFeed(
    modifier: Modifier,
    userList : List<User>
    
){
    LazyColumn {
        items(
            userList
        ){
            BlogContainer(modifier = modifier, user = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    BlogContainer(modifier = Modifier, user = User(
            id = 123,
            username="susan",
            lastSeen="2021-06-20T15:04:27+00:00",
            aboutMe="Hello, my name is Susan!",
            postCount=7,
            followerCount=35,
            followingCount=21,
            links=Links(
                self="/api/users/123",
                followers="/api/users/123/followers",
                following="/api/users/123/following",
                avatar="https://www.gravatar.com/avatar/"
            )
        )
    )
}