package com.example.mymvpapp.presenter.present

import com.example.mymvpapp.model.Login
import com.example.mymvpapp.presenter.contract.ILogin

class LoginPresenter(var view: ILogin.View) : ILogin.Presenter {


    override fun login(username: String, password: String) {

        val loginModel = Login()

        if (loginModel.username == username && loginModel.password == password) {
            view.onSuccess("Login is Ok(from presenter)")
        } else {
            view.onFail("Login is Fail(from Presenter)")
        }
    }
}