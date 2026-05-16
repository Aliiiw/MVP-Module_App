# MVP Module App

MVP Module App is a Kotlin Android sample that demonstrates the Model-View-Presenter pattern with a small Jetpack Compose UI and Retrofit-based network layer. The app talks to the JSONPlaceholder API, loads posts, and includes presenter flows for login, listing posts, fetching a single post, loading comments, and creating a new post.

The actual Android project is stored in the `MyMVPApp/` directory.

## Features

- Android app written in Kotlin
- Jetpack Compose UI for displaying posts
- MVP-style contract and presenter packages
- Retrofit API client for JSONPlaceholder
- Gson converter for JSON parsing
- OkHttp logging interceptor
- Models for posts, comments, and a simple login object
- Presenter contracts for login, all posts, single post, comments, and creating posts
- Network error, API error, and success callbacks through presenter/view interfaces
- Internet permission configured in the Android manifest
- Gradle wrapper included

## Project Structure

```text
.
+-- README.md
+-- MyMVPApp/
+    +-- build.gradle
+    +-- settings.gradle
+    +-- gradlew
+    +-- app/
+        +-- build.gradle
+        +-- src/main/
+            +-- AndroidManifest.xml
+            +-- java/com/example/mymvpapp/
+                +-- MainActivity.kt
+                +-- model/
+                +-- network/
+                +-- presenter/
+                |   +-- contract/
+                |   +-- present/
+                +-- ui/theme/
+```

## Architecture

The code follows a compact MVP structure:

```text
Compose UI / View callbacks
        |
        v
Presenter contracts
        |
        v
Presenter implementations
        |
        v
Retrofit ApiInterface
        |
        v
JSONPlaceholder API
```

`MainActivity.kt` acts as the current view layer. It creates presenter instances with anonymous contract implementations and updates/logs results through callback methods.

## Main Components

| File | Purpose |
| --- | --- |
| `MainActivity.kt` | Compose entry point, post list UI, and sample presenter calls. |
| `model/Post.kt` | Data model for JSONPlaceholder posts. |
| `model/Comment.kt` | Data model for JSONPlaceholder comments. |
| `model/Login.kt` | Simple local login model with default username/password values. |
| `network/ApiClient.kt` | Retrofit and OkHttp client setup. |
| `network/ApiInterface.kt` | HTTP endpoint definitions. |
| `presenter/contract/ILogin.kt` | Login view/presenter contract. |
| `presenter/contract/IPost.kt` | Contract for loading all posts. |
| `presenter/contract/ISinglePost.kt` | Contract for loading one post by id. |
| `presenter/contract/IComments.kt` | Contract for loading comments by post id. |
| `presenter/contract/INewPost.kt` | Contract for creating a new post. |
| `presenter/present/*.kt` | Presenter implementations for each contract. |

## API Layer

The app uses JSONPlaceholder as its sample backend:

```text
https://jsonplaceholder.typicode.com
```

Configured endpoints:

| Method | Path | Function | Purpose |
| --- | --- | --- | --- |
| `GET` | `/posts` | `getAllPosts()` | Load all posts. |
| `GET` | `/posts/{postId}` | `getPostById(postId)` | Load one post. |
| `GET` | `/comments?postId={id}` | `getAllPostComments(postId)` | Load comments for a post. |
| `POST` | `/posts` | `createNewPost(newPost)` | Send a new post body. |

## Presenter Flows

### Login

`LoginPresenter` checks the input against the local `Login` model defaults:

```text
username = username
password = 123
```

It returns success or failure through `ILogin.View`.

### All Posts

`PostPresenter` calls `ApiClient.api.getAllPosts()` and returns `List<Post>` through `IPost.View.onSuccess`.

### Single Post

`SinglePostPresenter` calls `getPostById(postId)` and returns a `Post` through `ISinglePost.View.onSuccess`.

### Comments

`CommentPresenter` calls `getAllPostComments(postId)` and returns `List<Comment>` through `IComments.View.onSuccess`.

### Create Post

`NewPostPresenter` calls `createNewPost(newPost)` and returns a success message containing the response post id.

## Current UI

The active UI path in `MainActivity.kt` loads all posts when the lifecycle reaches created state:

```kotlin
postList = getAllPostsRequest()
```

`PostView` renders the result in a `LazyColumn`. Each post appears in a blue block with a white title and dark body text.

Other sample calls are present but commented out:

```kotlin
getPostById(postId = "36")
getAllPostComments(postId = 2)
createNewPost(newPost = newPost)
Login(username = "username", password = "123")
```

## Tech Stack

- Kotlin
- Android
- Jetpack Compose
- Compose Material
- Retrofit `2.9.0`
- Gson converter `2.9.0`
- Gson `2.9.1`
- OkHttp logging interceptor `4.10.0`
- Gradle wrapper
- Android Gradle Plugin `7.4.2`
- Kotlin Android plugin `1.7.0`
- Compose UI `1.2.0`
- Compose compiler extension `1.2.0`

## Android Configuration

| Setting | Value |
| --- | --- |
| Namespace | `com.example.mymvpapp` |
| Application ID | `com.example.mymvpapp` |
| Min SDK | `21` |
| Target SDK | `33` |
| Compile SDK | `33` |
| Version | `1.0` |
| Main activity | `com.example.mymvpapp.MainActivity` |
| Permission | `android.permission.INTERNET` |

The manifest also enables cleartext traffic, although the configured sample API is HTTPS.

## How to Run

Open this folder in Android Studio:

```text
MyMVPApp/
```

Then sync Gradle and run the `app` configuration on an emulator or Android device.

From the command line, with Android SDK configured:

```bash
cd MyMVPApp
./gradlew :app:assembleDebug
```

To compile Kotlin only:

```bash
cd MyMVPApp
ANDROID_HOME=/path/to/Android/Sdk ./gradlew :app:compileDebugKotlin
```

## Build Check

This command was checked successfully in the local environment:

```bash
ANDROID_HOME=/Users/alirahimi/Library/Android/sdk ./gradlew :app:compileDebugKotlin
```

Result:

```text
BUILD SUCCESSFUL
```

## Notes

- This is a learning/sample project for MVP architecture, not a production networking app.
- The UI currently demonstrates the all-posts flow; other presenter flows are available through helper functions in `MainActivity.kt`.
- `ApiClient.BASE_URL` is missing a trailing slash. Retrofit normally expects base URLs to end with `/`, so runtime API usage may need `https://jsonplaceholder.typicode.com/`.
- `posts` is a top-level `lateinit` variable; if the network request fails before assignment, returning it can crash.
- The default Android starter tests are still present and do not cover the MVP flows.

## Possible Improvements

- Add the missing trailing slash to the Retrofit base URL
- Replace the top-level `lateinit var posts` with explicit UI loading/error state
- Move presenter creation out of composable/helper functions
- Add loading and error UI states
- Add tests for presenter success, API error, and exception paths
- Split the UI into smaller Compose components
- Add dependency injection for `ApiInterface`
- Remove unused imports and commented sample code when finalizing the app
