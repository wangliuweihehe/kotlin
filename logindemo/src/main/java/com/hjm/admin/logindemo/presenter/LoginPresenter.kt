package com.hjm.admin.logindemo.presenter

import com.hjm.admin.logindemo.Bean.LoginResponse
import com.hjm.admin.logindemo.Bean.RegisterResponse

/**
 * Created by admin on 2017/11/1.
 */

interface LoginPresenter {
    fun login(username: String, password: String)
       interface onLoginListener {
        fun loginSuccess(result: LoginResponse)
        fun loginFaild(message: String?)
    }

    fun register(username: String, password: String, email: String)
    interface onReginsterListener {
        fun registerSuccess(result: RegisterResponse)
        fun registerFaild(message: String?)
    }
}
