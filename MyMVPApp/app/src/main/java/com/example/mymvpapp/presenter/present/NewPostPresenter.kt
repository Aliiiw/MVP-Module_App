package com.example.mymvpapp.presenter.present


import com.example.mymvpapp.model.Post
import com.example.mymvpapp.network.ApiClient
import com.example.mymvpapp.presenter.contract.INewPost


class NewPostPresenter(var view: INewPost.View) : INewPost.Presenter {

    override suspend fun createNewPost(newPost: Post) {
        val response = try {
            ApiClient.api.createNewPost(newPost = newPost)
        } catch (e: Exception) {
            view.onFail("Fail: " + e.message)
            return
        }

        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { responsePost ->
                view.onSuccess("Post with id: ${responsePost.id} created")
            }
        } else {
            view.onError("error")
        }
    }
}