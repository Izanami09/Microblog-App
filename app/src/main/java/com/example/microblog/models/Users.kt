package com.example.microblog.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersResponse(
    @SerialName("_links")
    val links: NavigationLinks,
    @SerialName("_meta")
    val meta: Meta,
    val items: List<User>
)

@Serializable
data class Meta(
    val page: Int,
    @SerialName("per_page") val perPage: Int,
    @SerialName("total_items") val totalItems: Int,
    @SerialName("total_pages") val totalPages: Int
)

@Serializable
data class NavigationLinks(
    val next: String?,
    val prev: String?,
    val self: String
)

@Serializable
data class UserLinks(
    val avatar: String,
    val followers: String,
    val following: String,
    val self: String
)
