package com.example.mymvpapp.model

data class PostResponseModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)