package com.hjm.admin.mykotlin.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hjm.admin.mykotlin.R
import com.hjm.admin.mykotlin.views.adapter.FindFunnyTrailersAdapter
import com.hjm.admin.mykotlin.views.chAllAsyncToMainThread
import com.hjm.admin.mykotlin.views.networkbusiness.NetWorkRealCallMtime
import kotlinx.android.synthetic.main.fragment_find_funny_trailers.*

/**
 * Created by admin on 2017/11/3.
 */
class FindFunny_TrailersFragment : Fragment() {

    val mAdapter: FindFunnyTrailersAdapter by lazy {
        FindFunnyTrailersAdapter(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_find_funny_trailers, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_all_trailer.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        recycler_all_trailer.addItemDecoration()
        recycler_all_trailer.adapter = mAdapter
        swipe_refresh_trailer.isRefreshing = true
        swipe_refresh_trailer.setOnRefreshListener {
            requestData()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        recycler_all_trailer?.let {
            recycler_all_trailer.visibility = View.GONE
            recycler_all_trailer.scrollToPosition(0)
            swipe_refresh_trailer.isRefreshing = true
        }
        if (isVisibleToUser) {
            requestData()
        } else {
            onStop()
        }
    }

    fun requestData() {
        NetWorkRealCallMtime.newInstance()
                .getFindFunnyService()
                .requestFunnyTrailerList()
                .chAllAsyncToMainThread()
                .subscribe {
                    mAdapter.insertAllTrailers(it)
                    recycler_all_trailer.visibility = View.VISIBLE
                    swipe_refresh_trailer.isRefreshing = false
                }
    }

    override fun onStop() {
        super.onStop()
        Glide.get(activity).clearMemory()
    }

    companion object {
        fun newInstance(): FindFunny_TrailersFragment = FindFunny_TrailersFragment()
    }
}