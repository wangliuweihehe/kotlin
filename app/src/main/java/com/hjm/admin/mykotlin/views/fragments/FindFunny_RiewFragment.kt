package com.hjm.admin.mykotlin.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hjm.admin.mykotlin.R
import com.hjm.admin.mykotlin.views.adapter.FindFunnyReviewAdapter
import com.hjm.admin.mykotlin.views.chAllAsyncToMainThread
import com.hjm.admin.mykotlin.views.networkbusiness.NetWorkRealCallMtime
import kotlinx.android.synthetic.main.fragment_find_funny_review.*

/**
 * Created by admin on 2017/11/3.
 */
class FindFunny_RiewFragment : Fragment() {
    val mAdapter: FindFunnyReviewAdapter by lazy {
        FindFunnyReviewAdapter(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_find_funny_review, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        recyclerview_funnyreview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerview_funnyreview.adapter = mAdapter
        swipe_refresh_funnyreview.isRefreshing = true

        swipe_refresh_funnyreview.setOnRefreshListener {
            requestData()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        recyclerview_funnyreview?.let {
            swipe_refresh_funnyreview.isRefreshing = true
            recyclerview_funnyreview.visibility = View.GONE
            recyclerview_funnyreview.scrollToPosition(0)
        }
        if (isVisibleToUser) {
            requestData()
        }
    }

    fun requestData() {
        NetWorkRealCallMtime.newInstance()
                .getFindFunnyService()
                .requestFunnyReview()
                .chAllAsyncToMainThread()
                .subscribe {
                    swipe_refresh_funnyreview.isRefreshing = false
                    recyclerview_funnyreview.visibility = View.VISIBLE
                    mAdapter.insertAllReviews(it)
                }
    }

    companion object {
        fun newInstance(): FindFunny_RiewFragment = FindFunny_RiewFragment()
    }
}