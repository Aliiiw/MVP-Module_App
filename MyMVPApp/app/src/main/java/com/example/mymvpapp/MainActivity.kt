package com.example.mymvpapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.mymvpapp.model.Comment
import com.example.mymvpapp.model.Post
import com.example.mymvpapp.presenter.contract.*
import com.example.mymvpapp.presenter.present.*
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

                    //Login(username = "username", password = "123")

                    lifecycleScope.launchWhenCreated {
                        //getAllPostsRequest()
                        //getPostById(postId = "36")
                        //getAllPostComments(postId = 2)

                        val newPost = Post(
                            id = 101,
                            title = "Say hello",
                            userId = 5,
                            body = "Hi Aliiiw"
                        )

                        createNewPost(newPost = newPost)
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
        override fun onSuccess(postList: List<Post>) {
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

        override fun onSuccess(singlePost: Post) {

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

suspend fun getAllPostComments(postId: Int) {

    val presenter = CommentPresenter(object : IComments.View {

        override fun onSuccess(commentList: List<Comment>) {
            commentList.forEach { comment ->
                Log.e("2323", comment.body)
            }
        }

        override fun onError(message: String) {
            Log.e("2323", message)
        }

        override fun onFail(message: String) {
            Log.e("2323", message)
        }

    })

    presenter.getAllPostComments(postId = postId)

}

suspend fun createNewPost(newPost: Post) {

    val presenter = NewPostPresenter(object : INewPost.View {

        override fun onSuccess(post: String) {
            Log.e("2323", post)
        }

        override fun onError(message: String) {
            Log.e("2323", message)
        }

        override fun onFail(message: String) {
            Log.e("2323", message)
        }

    })

    presenter.createNewPost(newPost = newPost)

}