package com.example.mymvpapp.presenter.contract

import com.example.mymvpapp.model.PostResponseModel

interface IPost {

    interface View {
        fun onSuccess(postList: List<PostResponseModel>)
        fun onError(message: String)
        fun onFail(message: String)
    }

    interface Presenter{
        suspend fun getAllPostsRequest()
    }
}