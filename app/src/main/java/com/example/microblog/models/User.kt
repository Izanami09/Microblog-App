package com.example.microblog.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("_links")
    val links: Links,

    @SerialName("about_me")
    val aboutMe: String?,

    @SerialName("follower_count")
    val followerCount: Int,

    @SerialName("following_count")
    val followingCount: Int,

    val id: Int,

    @SerialName("last_seen")
    val lastSeen: String,

    @SerialName("post_count")
    val postCount: Int,

    val username: String
)

@Serializable
data class Links(
    val avatar: String,

    val followers: String,

    val following: String,

    val self: String
)
