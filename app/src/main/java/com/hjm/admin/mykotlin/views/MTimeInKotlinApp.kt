package com.hjm.admin.mykotlin.views

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hjm.admin.mykotlin.views.networkbusiness.HotMovieSoonComeAllDate
import com.hjm.admin.mykotlin.views.networkbusiness.HotMovieSoonComeItemData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by admin on 2017/10/28.
 */
fun ImageView.chAllDisplayImage(activity: Context, strUrl: String?) {
    Glide.with(activity).load(strUrl).into(this).onDestroy()
}

fun <T : Any?> Observable<T>.chAllAsyncToMainThread(): Observable<T> {
    return this.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
}

//context 启动活动ext
inline fun <reified T : Activity> Context.chAllStartActivity(supportParam: Map<String, String>): Unit {
    val intent = Intent(this, T::class.java)
    for ((key, value) in supportParam) {
        intent.putExtra(key, value)
    }
    startActivity(intent)
}

fun chAllInflateView(ctx: Context, nResId: Int): View = LayoutInflater.from(ctx).inflate(nResId, null)
class MTimeInKotlinApp : Application() {
    var mComeSoonMonData: List<HotMovieSoonComeItemData>? = null
        set(value) {
            field = value
        }
}