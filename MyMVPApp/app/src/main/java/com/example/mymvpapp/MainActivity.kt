package com.example.mymvpapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.mymvpapp.model.Comment
import com.example.mymvpapp.model.Post
import com.example.mymvpapp.presenter.contract.*
import com.example.mymvpapp.presenter.present.*
import com.example.mymvpapp.ui.theme.MyMVPAppTheme

lateinit var posts: List<Post>

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMVPAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    var postList by remember {
                        mutableStateOf(emptyList<Post>())
                    }

                    lifecycleScope.launchWhenCreated {

                        postList = getAllPostsRequest()
                        //getPostById(postId = "36")
                        //getAllPostComments(postId = 2)

//                        val newPost = Post(
//                            id = 101,
//                            title = "Say hello",
//                            userId = 5,
//                            body = "Hi Aliiiw"
//                        )

                        //createNewPost(newPost = newPost)
                    }

                    Column() {
                        PostView(postList = postList)
                    }


                    //Login(username = "username", password = "123")


                }
            }
        }
    }
}


@Composable
fun PostView(postList: List<Post>) {
    LazyColumn() {
        items(postList) { post ->
            Column(modifier = Modifier
                .padding(12.dp)
                .background(Color.Blue)) {

                Text(text = post.title, color = Color.White)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = post.body, color = Color.DarkGray)
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

suspend fun getAllPostsRequest(): List<Post> {
    val presenter = PostPresenter(object : IPost.View {
        override fun onSuccess(postList: List<Post>) {
            posts = postList
//            postList.forEach { post ->
//                Log.e("2323", post.title)
//            }
        }

        override fun onError(message: String) {
            Log.e("2323", message)
        }

        override fun onFail(message: String) {
            Log.e("2323", message)
        }

    })

    presenter.getAllPostsRequest()

    return posts

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