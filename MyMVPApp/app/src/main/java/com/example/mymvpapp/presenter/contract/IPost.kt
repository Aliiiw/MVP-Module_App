package com.example.mymvpapp.presenter.contract

import com.example.mymvpapp.model.Post

interface IPost {

    interface View {
        fun onSuccess(postList: List<Post>)
        fun onError(message: String)
        fun onFail(message: String)
    }

    interface Presenter{
        suspend fun getAllPostsRequest()
    }
}