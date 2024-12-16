package com.example.microblog.ui.screens.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.microblog.models.Links
import com.example.microblog.models.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier, userList: List<User>){
    //Creating Bottom Aligned App Bar
    Scaffold(
        topBar = {CenterAlignedTopAppBar(title = { Text(text = "Microblog")})},
        modifier = modifier
    ){
        topBarPaddingValue -> BlogFeed(modifier = Modifier.padding(topBarPaddingValue), userList = userList )
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
                horizontalArrangement = Arrangement.Start
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(user.links.avatar).build(),
                    contentDescription = "Profile Picture Of The Author",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .width(40.dp)
                        .fillMaxHeight()

                )
                Text(text = user.username, modifier=modifier.padding(4.dp))
            }
            Text(text = user.lastSeen)
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