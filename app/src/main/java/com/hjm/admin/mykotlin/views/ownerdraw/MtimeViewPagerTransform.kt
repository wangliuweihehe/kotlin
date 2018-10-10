package com.hjm.admin.mykotlin.views.ownerdraw

import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by admin on 2017/11/21.
 */
class MtimeViewPagerTransform : ViewPager.PageTransformer {
    override fun transformPage(page: View?, position: Float) {
        val MIN_SCALE = 0.9f
        val MIN_ALPHA = 0.4f
        if (position < -1 || position > 1) {
            page?.alpha = MIN_ALPHA
            page?.scaleX = MIN_SCALE
            page?.scaleY = MIN_SCALE
        } else if (position <= 1) {
            val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
            if (position < 0) {
                val scaleX = 1 + 0.1f * position
                page?.scaleX = scaleX
                page?.scaleY = scaleX
            } else {
                val scaleX = 1 - 0.1f * position
                page?.scaleX = scaleX
                page?.scaleY = scaleX
            }
            page?.alpha = MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA)
        }
    }

}