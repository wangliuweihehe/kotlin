package com.hjm.admin.logindemo.presenter

import com.hjm.admin.logindemo.Bean.LoginResponse
import com.hjm.admin.logindemo.Bean.RegisterResponse
import com.hjm.admin.logindemo.model.LoginModel
import com.hjm.admin.logindemo.model.LoginModelImpl
import com.hjm.admin.logindemo.view.LoginView

/**
 * Created by admin on 2017/11/1.
 */
class LoginPresenterImpl(val loginView: LoginView) : LoginPresenter, LoginPresenter.onLoginListener, LoginPresenter.onReginsterListener {
    val mLoginModel: LoginModel

    init {
        mLoginModel = LoginModelImpl()
    }

    override fun loginSuccess(result: LoginResponse) {
        loginView.loginSuccess(result)
    }

    override fun loginFaild(message: String?) {
        loginView.loginFaild(message)
    }

    override fun registerSuccess(result: RegisterResponse) {
        loginView.registerSuccess(result)
    }

    override fun registerFaild(message: String?) {
        loginView.registerFaild(message)
    }

    override fun login(username: String, password: String) {
        mLoginModel.login(this, username, password)
    }

    override fun register(username: String, password: String, email: String) {
        mLoginModel.register(this, username, password, email)
    }
}