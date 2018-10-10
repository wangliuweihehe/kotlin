package com.hjm.admin.mykotlin.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hjm.admin.mykotlin.R
import com.hjm.admin.mykotlin.views.adapter.HotMovieCinemaAdapter
import com.hjm.admin.mykotlin.views.chAllAsyncToMainThread
import com.hjm.admin.mykotlin.views.networkbusiness.NetWorkRealCallMtime
import kotlinx.android.synthetic.main.fragment_hot_movie_cinemas.*

/**
 * Created by admin on 2017/11/3.
 */
class HotMovie_CinemasFragment : Fragment() {
    val mAdapter: HotMovieCinemaAdapter by lazy {
        HotMovieCinemaAdapter(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_hot_movie_cinemas, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NetWorkRealCallMtime.newInstance()
                .getHotMovieService()
                .requestAllCinemas("290")
                .chAllAsyncToMainThread()
                .subscribe({
                    mAdapter.insertCinemaData(it.toMutableList())
                }, { t ->
                    Log.e("throwble==", t.toString())
                })
        cinema_recyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        cinema_recyclerview.adapter = mAdapter
    }

    companion object {
        fun newInstance(): HotMovie_CinemasFragment = HotMovie_CinemasFragment()
    }
}