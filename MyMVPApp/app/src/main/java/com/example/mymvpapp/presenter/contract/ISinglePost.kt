package com.example.mymvpapp.presenter.contract

import com.example.mymvpapp.model.Post

interface ISinglePost {

    interface View {
        fun onSuccess(singlePost: Post)
        fun onError(message: String)
        fun onFail(message: String)
    }

    interface Presenter {
        suspend fun getPostById(postId: String)
    }
}