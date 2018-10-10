package com.hjm.admin.mykotlin.views.networkbusiness

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by admin on 2017/10/28.
 */
class NetWorkRealCallMtime private constructor() {
    private var mRetrofit: Retrofit

    init {
        val okhttpClient = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build()
        mRetrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient).build()
    }

    fun getHotMovieService() = mRetrofit.create(IHotMovieService::class.java)!!
    fun getFindFunnyService()=mRetrofit.create(IFindFunnyService::class.java)!!
    fun getRankListService()=mRetrofit.create(IRankListService::class.java)!!
    companion object {
        var BASE_URL = "https://api-m.mtime.cn/"
        val OBJECT_INSTANCE: NetWorkRealCallMtime by lazy {
            NetWorkRealCallMtime()
        }

        fun newInstance(): NetWorkRealCallMtime = OBJECT_INSTANCE
    }
}