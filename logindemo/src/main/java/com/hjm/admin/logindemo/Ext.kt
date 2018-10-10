package com.hjm.admin.logindemo

import android.util.Log

/**
 * Created by admin on 2017/11/1.
 */
fun loge(tag: String, content: String?) {
    Log.e(tag, content ?: tag)
}