package com.example.mymvpapp.presenter.contract

interface ILogin {

    interface View {
        fun onSuccess(message: String)
        fun onFail(message: String)
    }

    interface Presenter {
        fun login(username: String, password: String)
    }
}