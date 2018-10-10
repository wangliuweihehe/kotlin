package com.hjm.admin.logindemo.view

import com.hjm.admin.logindemo.Bean.LoginResponse
import com.hjm.admin.logindemo.Bean.RegisterResponse

/**
 * Created by admin on 2017/11/1.
 */
interface LoginView {
    fun loginSuccess(result: LoginResponse)
    fun loginFaild(message: String?)
    fun registerSuccess(result: RegisterResponse)
    fun registerFaild(message: String?)
}