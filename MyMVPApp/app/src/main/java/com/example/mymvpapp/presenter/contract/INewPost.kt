package com.example.mymvpapp.presenter.contract

import com.example.mymvpapp.model.Post

interface INewPost {

    interface View {
        fun onSuccess(post: String)
        fun onError(message: String)
        fun onFail(message: String)
    }

    interface Presenter {
        suspend fun createNewPost(newPost: Post)
    }
}