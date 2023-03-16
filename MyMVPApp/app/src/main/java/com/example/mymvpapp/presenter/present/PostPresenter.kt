package com.example.mymvpapp.presenter.present

import android.util.Log
import com.example.mymvpapp.network.ApiClient
import com.example.mymvpapp.presenter.contract.IPost

class PostPresenter(var view: IPost.View) : IPost.Presenter {
    override suspend fun getAllPostsRequest() {
        val response = try {
            ApiClient.api.getAllPosts()
        } catch (e: Exception) {
            view.onFail("Fail: " + e.message)
            return
        }

        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { allPost ->
                view.onSuccess(allPost)
            }
        } else {
            view.onError("error")
        }
    }
}