package com.hjm.admin.logindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import cn.pedant.SweetAlert.SweetAlertDialog
import com.hjm.admin.logindemo.Bean.LoginResponse
import com.hjm.admin.logindemo.Bean.RegisterResponse
import com.hjm.admin.logindemo.presenter.LoginPresenter
import com.hjm.admin.logindemo.presenter.LoginPresenterImpl
import com.hjm.admin.logindemo.view.LoginView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoginView, View.OnClickListener {


    var loginPresenter: LoginPresenter? = null
    var dialog: SweetAlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginPresenter = LoginPresenterImpl(this)
        login.setOnClickListener(this)
        register.setOnClickListener(this)
    }

    override fun loginSuccess(result: LoginResponse) {
    }

    override fun loginFaild(message: String?) {
    }

    override fun registerSuccess(result: RegisterResponse) {
    }

    override fun registerFaild(message: String?) {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login -> if (checkContent(false)) {
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
//        var cancel = false
//        var focusView: View? = null
        if (!login) {
            if (TextUtils.isEmpty(email.text.toString())) {

            } else {

            }
        }
        return false
    }
}
