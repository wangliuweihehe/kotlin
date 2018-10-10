package com.hjm.admin.logindemo.api

import com.hjm.admin.logindemo.Bean.LoginResponse
import com.hjm.admin.logindemo.Bean.RegisterResponse
import com.hjm.admin.logindemo.constant.Constant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by admin on 2017/11/1.
 */
interface RetrofitService {
    @GET("rigister")
    fun userRegister(
            @Query("username") username: String,
            @Query("password") password: String,
            @Query("email") email: String,
            @Query("key") key: String = Constant.key
    ): Observable<RegisterResponse>

    @GET("login")
    fun userLogin(@Query("username") username: String, @Query("password") password: String, @Query("key") key: String = Constant.key): Observable<LoginResponse>
}