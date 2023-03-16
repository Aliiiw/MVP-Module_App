package com.example.mymvpapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.mymvpapp.model.PostResponseModel
import com.example.mymvpapp.network.ApiClient
import com.example.mymvpapp.presenter.contract.ILogin
import com.example.mymvpapp.presenter.contract.IPost
import com.example.mymvpapp.presenter.contract.ISinglePost
import com.example.mymvpapp.presenter.present.LoginPresenter
import com.example.mymvpapp.presenter.present.PostPresenter
import com.example.mymvpapp.presenter.present.SinglePostPresenter
import com.example.mymvpapp.ui.theme.MyMVPAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMVPAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Login(username = "username", password = "123")

                    lifecycleScope.launchWhenCreated {
                        //getAllPostsRequest()
                        getPostById(postId = "36")
                    }

                }
            }
        }
    }
}

@Composable
fun Login(username: String, password: String) {
    val presenter = LoginPresenter(object : ILogin.View {
        override fun onFail(message: String) {
            Log.e("2323", message)
        }

        override fun onSuccess(message: String) {
            Log.e("2323", message)
        }
    })

    presenter.login(username = username, password = password)
}


suspend fun getAllPostsRequest() {
    val presenter = PostPresenter(object : IPost.View {
        override fun onSuccess(postList: List<PostResponseModel>) {
            postList.forEach { post ->
                Log.e("2323", post.title)
            }
        }

        override fun onError(message: String) {
            Log.e("2323", message)
        }

        override fun onFail(message: String) {
            Log.e("2323", message)
        }

    })

    presenter.getAllPostsRequest()

}

suspend fun getPostById(postId: String) {
    val presenter = SinglePostPresenter(object : ISinglePost.View {

        override fun onSuccess(singlePost: PostResponseModel) {

            Log.e("2323", singlePost.title)

        }

        override fun onError(message: String) {
            Log.e("2323", message)
        }

        override fun onFail(message: String) {
            Log.e("2323", message)
        }

    })

    presenter.getPostById(postId = postId)

}