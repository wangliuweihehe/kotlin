package com.hjm.admin.mykotlin.views.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hjm.admin.mykotlin.R
import com.hjm.admin.mykotlin.views.networkbusiness.CinemaData
import com.zhy.autolayout.utils.AutoUtils

/**
 * Created by admin on 2017/11/3.
 */
class HotMovieCinemaAdapter(val ctx: Context) : RecyclerView.Adapter<HotMovieCinemaAdapter.innerViewHolder>() {
    var m_listCinemas: MutableList<CinemaData> = mutableListOf()
    fun insertCinemaData(list: MutableList<CinemaData>) {
        this.m_listCinemas = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): innerViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_hot_movie_cinemas, parent, false)
        return innerViewHolder(view)
    }

    override fun onBindViewHolder(holder: innerViewHolder?, position: Int) {
        with(holder!!) {
            itemView.setOnClickListener {

            }
            cname.setText(m_listCinemas[position].cinameName)
            caddress.text = m_listCinemas[position].cinameName
        }
    }

    override fun getItemCount(): Int {
        return m_listCinemas.size
    }

    class innerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            AutoUtils.autoSize(itemView)
        }

        val cname = itemView.findViewById<TextView>(R.id.cinema_name)
        val caddress = itemView.findViewById<TextView>(R.id.cinema_address)
    }
}