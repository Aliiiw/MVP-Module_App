package com.example.mymvpapp.network

import com.example.mymvpapp.model.PostResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("/posts")
    suspend fun getAllPosts(): Response<List<PostResponseModel>>

    @GET("/{postId}")
    suspend fun getPostById(
        @Path(
            value = "postId",
            encoded = true
        ) postId: String
    ): Response<PostResponseModel>
}