package com.example.mymvpapp.presenter.present

import android.util.Log
import com.example.mymvpapp.network.ApiClient
import com.example.mymvpapp.presenter.contract.IPost
import com.example.mymvpapp.presenter.contract.ISinglePost

class SinglePostPresenter(var view: ISinglePost.View) : ISinglePost.Presenter {

    override suspend fun getPostById(postId: String) {
        val response = try {
            ApiClient.api.getPostById(postId = postId)
        } catch (e: Exception) {
            view.onFail("Fail: " + e.message)
            return
        }

        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { post ->
                view.onSuccess(post)
            }
        } else {
            view.onError("error")
        }
    }
}