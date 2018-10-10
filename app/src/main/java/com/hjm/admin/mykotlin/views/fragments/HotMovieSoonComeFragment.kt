package com.hjm.admin.mykotlin.views.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hjm.admin.mykotlin.R
import com.hjm.admin.mykotlin.views.adapter.HotMovieSoonComeAdapter
import com.hjm.admin.mykotlin.views.chAllAsyncToMainThread
import com.hjm.admin.mykotlin.views.networkbusiness.NetWorkRealCallMtime
import kotlinx.android.synthetic.main.fragment_hot_movie_sooncome.*

@RequiresApi(Build.VERSION_CODES.M)
/**
 * Created by admin on 2017/10/24.
 */
class HotMovieSoonComeFragment : Fragment() {
    val mAdapter: HotMovieSoonComeAdapter by lazy {
        HotMovieSoonComeAdapter(activity, { action ->
            val snackbar = Snackbar.make(snack_container, action, Snackbar.LENGTH_SHORT)
            snackbar.view.setBackgroundColor(Color.parseColor("#bf360c"))
            snackbar.view.findViewById<TextView>(R.id.snackbar_text).setTextAppearance(R.style.SnackbarTextStyle)
            snackbar.show()
        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_hot_movie_sooncome, container, false)
    }

    interface IWannaSeeListener {
        fun wannaSee(action: String)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sooncome_recyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        sooncome_recyclerview.adapter = mAdapter
        swipe_refresh.isRefreshing = true
        swipe_refresh.setOnRefreshListener {
            requestNetWorkData()
        }
        requestNetWorkData()
    }

    private fun requestNetWorkData() {
        NetWorkRealCallMtime.newInstance().
                getHotMovieService().
                requestSoonComeHotMovie("290")
                .chAllAsyncToMainThread()
                .subscribe({
                    swipe_refresh.isRefreshing = false
                    mAdapter.addAllData(it)
                }, { t ->
                    Log.e("throwble ==", t.toString())
                })
    }

    companion object {
        fun newInstance(): HotMovieSoonComeFragment = HotMovieSoonComeFragment()
    }
}