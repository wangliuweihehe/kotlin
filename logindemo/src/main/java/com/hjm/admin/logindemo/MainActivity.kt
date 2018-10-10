package com.hjm.admin.logindemo

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.hjm.admin.logindemo.Bean.LoginResponse
import com.hjm.admin.logindemo.Bean.RegisterResponse
import com.hjm.admin.logindemo.presenter.LoginPresenter
import com.hjm.admin.logindemo.presenter.LoginPresenterImpl
import com.hjm.admin.logindemo.view.LoginView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity(), LoginView, View.OnClickListener {
    var loginPresenter: LoginPresenter? = null
    var dialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginPresenter = LoginPresenterImpl(this)
        login.setOnClickListener(this)
        register.setOnClickListener(this)
    }

    override fun loginSuccess(result: LoginResponse) {
        dialog?.dismiss()
        Log.e("loginSuccess", result.msg)
    }

    override fun loginFaild(message: String?) {
        dialog?.dismiss()
        Log.e("loginFaild", message)
    }

    override fun registerSuccess(result: RegisterResponse) {
        dialog?.dismiss()
        Log.e("registerSuccess", result.msg)
    }

    override fun registerFaild(message: String?) {
        dialog?.dismiss()
        Log.e("registerFaild", message)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login ->
                if (checkContent(true)) {
                    dialog = ProgressDialog(this)
                    dialog?.setMessage("正在登陆...")
                    dialog?.setCancelable(false)
                    dialog?.show()
                    loginPresenter?.login(username.text.toString(), password.text.toString())
                }
            R.id.register ->
                if (checkContent(false)) {
                    dialog = ProgressDialog(this)
                    dialog?.setMessage("正在注册...")
                    dialog?.setCancelable(false)
                    dialog?.show()
                    loginPresenter?.register(username.text.toString(), password.text.toString(), email.text.toString())
                }
        }

    }

    /**
     * 判断
     */
    private fun checkContent(login: Boolean): Boolean {
        username.error = null
        password.error = null
        email.error = null
        var cancel = false
        var focusView: View? = null
        if (!login) {
            if (TextUtils.isEmpty(email.text.toString())) {
                email.error = "Email不能为空"
                focusView = email
                cancel = true
            } else if (!isEmail(email.text.toString())) {
                email.error = "Email格式不正确"
                focusView = email
                cancel = true
            }
        }
        if (TextUtils.isEmpty(password.text.toString())) {
            password.error = "密码不能为空"
            focusView = password
            cancel = true
        } else if (password.text.length < 6) {
            password.error = "密码不能小于6位"
            focusView = password
            cancel = true
        }
        if (cancel) {
            if (focusView != null)
                focusView.requestFocus()
        } else {
            return true
        }
        return false
    }

    fun isEmail(email: String?): Boolean {
        if (email == null) {
            return false
        }
        val regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}"
        val pattern = Pattern.compile(regex)
        return pattern.matcher(email).matches()
    }
}
