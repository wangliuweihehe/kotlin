package com.hjm.admin.logindemo.model

import com.hjm.admin.logindemo.presenter.LoginPresenter
import com.hjm.admin.logindemo.util.RetrofitUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by admin on 2017/11/1.
 */
class LoginModelImpl : LoginModel {
    var mOnLoginListener: LoginPresenter.onLoginListener? = null
    var mOnRegisterListener: LoginPresenter.onReginsterListener? = null
    override fun login(onLoginListener: LoginPresenter.onLoginListener, username: String, password: String) {
        if (mOnLoginListener == null) {
            mOnLoginListener = onLoginListener
        }
        RetrofitUtils.retrofitService
                .userLogin(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    when (result.retCode) {
                        "200" ->
                            mOnLoginListener?.loginSuccess(result)
                        else ->
                            mOnLoginListener?.loginFaild(result.msg)
                    }
                }, { error ->
                    mOnLoginListener?.loginFaild(error.message)
                })
    }

    override fun register(onReginsterListener: LoginPresenter.onReginsterListener, username: String, password: String, email: String) {
        if (mOnRegisterListener == null) {
            mOnRegisterListener = onReginsterListener
        }
        RetrofitUtils
                .retrofitService
                .userRegister(username, password, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    when (result.retCode) {
                        "200" ->
                            onReginsterListener?.registerSuccess(result)
                        else -> onReginsterListener?.registerFaild(result.msg)
                    }
                }, { error ->
                    mOnRegisterListener?.registerFaild(error.message)
                })
    }

}