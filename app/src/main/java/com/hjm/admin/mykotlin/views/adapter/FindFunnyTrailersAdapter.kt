package com.hjm.admin.mykotlin.views.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.hjm.admin.mykotlin.R
import com.hjm.admin.mykotlin.views.chAllDisplayImage
import com.hjm.admin.mykotlin.views.chAllInflateView
import com.hjm.admin.mykotlin.views.networkbusiness.TrailerItem
import com.hjm.admin.mykotlin.views.networkbusiness.TrailersDataArray
import com.zhy.autolayout.utils.AutoUtils

/**
 * Created by admin on 2017/11/3.
 */
class FindFunnyTrailersAdapter(val ctx: Context) : RecyclerView.Adapter<FindFunnyTrailersAdapter.ViewHolder>() {
    var m_listTrailers: MutableList<TrailerItem>? = null

    fun insertAllTrailers(data: TrailersDataArray) {
        this.m_listTrailers = data.trailers.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        m_listTrailers?.let {
            with(holder!!) {
                funnytrailer_cover?.chAllDisplayImage(ctx, m_listTrailers?.get(position)?.coverImg)
                funnytrailer_allinfo?.text = "<<${m_listTrailers?.get(position)?.videoTitle}>>---${m_listTrailers?.get(position)?.summary}"
                itemView.setOnClickListener {

                }
                val ani = AnimationUtils.loadAnimation(ctx, R.anim.item_left_in)
                ani.interpolator = AnticipateOvershootInterpolator()
                itemView?.startAnimation(ani)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(chAllInflateView(ctx, R.layout.item_findfunny_trailer))
    }

    override fun getItemCount(): Int = m_listTrailers?.size ?: 0
    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        init {
            AutoUtils.autoSize(itemView)
        }

        val funnytrailer_cover = itemView?.findViewById<ImageView>(R.id.funnytrailer_cover)
        val funnytrailer_allinfo = itemView?.findViewById<TextView>(R.id.funnytrailer_allinfo)
    }
}