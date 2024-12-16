package com.example.microblog.models

import kotlinx.serialization.Serializable

@Serializable
data class Blog(
    val blogContent: String
)
