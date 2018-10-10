package com.hjm.admin.mykotlin.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hjm.admin.mykotlin.R
import com.hjm.admin.mykotlin.views.adapter.FindFunnyNewsAdapter
import com.hjm.admin.mykotlin.views.chAllAsyncToMainThread
import com.hjm.admin.mykotlin.views.networkbusiness.NetWorkRealCallMtime
import kotlinx.android.synthetic.main.fragment_find_funny_news.*
import kotlinx.android.synthetic.main.fragment_find_funny_trailers.*

/**
 * Created by admin on 2017/11/3.
 */
class FindFunny_NewsFragment : Fragment() {
    private val mAdapter: FindFunnyNewsAdapter?by lazy {
        FindFunnyNewsAdapter(activity)
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_find_funny_news, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_news.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recycler_news.adapter = mAdapter
        swipe_refresh_funnynews.isRefreshing = true
        swipe_refresh_funnynews.setOnRefreshListener {
            startRequestAdvertisementList()
        }
        startRequestAdvertisementList()
    }

    private fun startRequestAdvertisementList() {
        NetWorkRealCallMtime.newInstance().getFindFunnyService()
                .requestFunnyAdvertise()
                .chAllAsyncToMainThread()
                .subscribe {
                    mAdapter?.insertAdvertiseData(it.topPosters)
                    startRequestNewsList()
                }

    }

    private fun startRequestNewsList() {
        NetWorkRealCallMtime.newInstance().getFindFunnyService()
                .requestFunnyNewsList(1)
                .chAllAsyncToMainThread()
                .subscribe {
                    mAdapter?.insertNewsData(it)
                    recycler_news.visibility = View.VISIBLE
                    swipe_refresh_funnynews.isRefreshing = false
                }
    }

    companion object {
        fun newIntance(): FindFunny_NewsFragment = FindFunny_NewsFragment()
    }
}