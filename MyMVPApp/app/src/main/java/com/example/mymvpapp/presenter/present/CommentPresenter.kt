package com.example.mymvpapp.presenter.present

import android.util.Log
import com.example.mymvpapp.network.ApiClient
import com.example.mymvpapp.presenter.contract.IComments
import com.example.mymvpapp.presenter.contract.IPost

class CommentPresenter(var view: IComments.View) : IComments.Presenter {


    override suspend fun getAllPostComments(postId: Int) {
        val response = try {
            ApiClient.api.getAllPostComments(postId = postId)
        } catch (e: Exception) {
            view.onFail("Fail: " + e.message)
            return
        }

        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { allPostComments ->
                view.onSuccess(allPostComments)

            }
        } else {
            view.onError("error")
        }
    }
}