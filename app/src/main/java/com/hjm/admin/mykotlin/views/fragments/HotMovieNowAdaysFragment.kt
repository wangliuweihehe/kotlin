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
import com.hjm.admin.mykotlin.views.adapter.HotMovieNowadaysAdapter
import com.hjm.admin.mykotlin.views.chAllAsyncToMainThread
import com.hjm.admin.mykotlin.views.networkbusiness.HotMovieNowadaaysItemData
import com.hjm.admin.mykotlin.views.networkbusiness.NetWorkRealCallMtime
import kotlinx.android.synthetic.main.fragment_hot_movie_nowadays.*

@RequiresApi(Build.VERSION_CODES.M)
/**
 * Created by admin on 2017/11/2.
 */
class HotMovieNowAdaysFragment : Fragment() {
    val mHotAdapter: HotMovieNowadaysAdapter by lazy {
        HotMovieNowadaysAdapter(activity, { action ->
            val snackbar = Snackbar.make(snack_container_now, action, Snackbar.LENGTH_SHORT)
            snackbar.view.setBackgroundColor(Color.parseColor("#bf360e"))
            snackbar.view.findViewById<TextView>(R.id.snackbar_text).setTextAppearance(R.style.SnackbarTextStyle)
            snackbar.show()
        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_hot_movie_nowadays, container, false)
    }

    lateinit var m_listResponse: List<HotMovieNowadaaysItemData>
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        requestNetWorkData()
    }

    private fun initViews() {
        nowadays_recyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        nowadays_recyclerview.adapter = mHotAdapter
        swipe_refresh.isRefreshing = true
        swipe_refresh.setOnRefreshListener {
            lableScore.setColor(Color.BLACK)
            lableHot.setColor(Color.BLACK)
            requestNetWorkData()
        }
        lableScore.setText("按照分数排序")
        lableHot.setText("按热度排序")
        lableScore.setOnClickListener {
            lableHot.setColor(Color.BLACK)
            nowadays_recyclerview.scrollToPosition(0)
            mHotAdapter.insertDatas(m_listResponse.sortedByDescending { it.r }.toMutableList())

        }
        lableHot.setOnClickListener {
            nowadays_recyclerview.scrollToPosition(0)
            lableScore.setColor(Color.BLACK)
            mHotAdapter.insertDatas(m_listResponse.sortedByDescending { it.wantedCount }.toMutableList())

        }
    }

    private fun requestNetWorkData() {
        NetWorkRealCallMtime.newInstance()
                .getHotMovieService()
                .requestNowadaysHotMovie("290")
                .chAllAsyncToMainThread()
                .subscribe({ t ->
                    swipe_refresh.isRefreshing = false
                    m_listResponse = t.ms
                    mHotAdapter.insertDatas(t.ms.toMutableList())
                }, { t -> Log.e("throwble==", t.toString()) })
    }

    companion object {
        fun newInstance(): HotMovieNowAdaysFragment = HotMovieNowAdaysFragment()
    }
}