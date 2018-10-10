package com.hjm.admin.logindemo.model

import com.hjm.admin.logindemo.presenter.LoginPresenter

/**
 * Created by admin on 2017/11/1.
 */

interface LoginModel {
    fun login(onLoginListener: LoginPresenter.onLoginListener, username: String, password: String)
    fun register(onReginsterListener: LoginPresenter.onReginsterListener, username: String, password: String, email: String)
}
