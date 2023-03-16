package com.example.mymvpapp.network

import com.example.mymvpapp.model.Comment
import com.example.mymvpapp.model.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/posts")
    suspend fun getAllPosts(): Response<List<Post>>

    @GET("/posts/{postId}")
    suspend fun getPostById(
        @Path(
            value = "postId",
            encoded = true
        ) postId: String
    ): Response<Post>

    @GET("/comments")
    suspend fun getAllPostComments(
        @Query("postId") postId: Int
    ): Response<List<Comment>>

    @POST("/posts")
    suspend fun createNewPost(
        @Body newPost: Post
    ): Response<Post>
}