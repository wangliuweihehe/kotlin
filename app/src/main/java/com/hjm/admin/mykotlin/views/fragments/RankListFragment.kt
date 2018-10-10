package com.hjm.admin.mykotlin.views.fragments

import android.app.ActionBar
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.hjm.admin.mykotlin.R
import com.hjm.admin.mykotlin.views.chAllAsyncToMainThread
import com.hjm.admin.mykotlin.views.chAllDisplayImage
import com.hjm.admin.mykotlin.views.networkbusiness.NetWorkRealCallMtime
import com.hjm.admin.mykotlin.views.networkbusiness.WeeklyMostFocusItem
import com.hjm.admin.mykotlin.views.ownerdraw.MtimeViewPagerTransform
import kotlinx.android.synthetic.main.fragment_rank_list.*

/**
 * Created by admin on 2017/11/5.
 */
class RankListFragment : Fragment() {
    lateinit var m_listWeeklyFocus: MutableList<WeeklyMostFocusItem>
    lateinit var m_listWeeklyExpect: MutableList<WeeklyMostFocusItem>
    lateinit var m_listChHighestRating: MutableList<WeeklyMostFocusItem>
    val m_listWeeklyFocusCover: MutableList<ImageView>by lazy {
        MutableList(m_listWeeklyFocus.size, { imageView() })
    }

    private fun imageView(): ImageView {
        val iv = ImageView(activity)
        iv.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        iv.scaleType = ImageView.ScaleType.CENTER_CROP

        return iv
    }

    val m_listWeekExpectCover: MutableList<ImageView>by lazy {
        MutableList(m_listWeeklyExpect.size, { imageView() })
    }
    val m_listChHignestRatingCover: MutableList<ImageView>by lazy {
        MutableList(10, {imageView() })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_rank_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestWeeklyFocus()
        requestWeeklyMostExpect()
        requestChHighestRating()
    }

    private fun requestChHighestRating() {
        NetWorkRealCallMtime.newInstance().getRankListService()
                .requestTopList(2066)
                .chAllAsyncToMainThread()
                .subscribe({
                    title_highestrating_ch.text = it.topList.summary
                    m_listChHighestRating = it.movies
                    initChHighestRatingViews()
                }, { t -> Log.e("net error", t.toString()) })


    }

    private fun requestWeeklyMostExpect() {
        NetWorkRealCallMtime.newInstance().getRankListService()
                .requestTopList(2069)
                .chAllAsyncToMainThread()
                .subscribe({
                    title_weeklyexpect.text = it.topList.summary
                    m_listWeeklyExpect = it.movies
                    initWeeklyExpectViews()
                }, { t -> Log.e("net error", t.toString()) })
    }

    private fun initWeeklyExpectViews() {
        m_listWeekExpectCover.forEachIndexed { index, imageView ->
            imageView.chAllDisplayImage(activity, m_listWeeklyExpect[index].posterUrl)
            imageView.setOnClickListener {
                //                activity.chAllstartActivity<MovieDetailActivity>(mapOf("movieid" to m_listWeeklyExpect[index].id!!.toString(), "moviename" to m_listWeeklyExpect[index].name!!))
            }
        }
        //
        viewpager_weeklyexpect.adapter = object : PagerAdapter() {
            override fun isViewFromObject(view: View?, view1: Any?): Boolean = view == view1
            override fun getCount(): Int = m_listWeeklyExpect.size
            override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
                container?.removeView(m_listWeekExpectCover[position])
            }

            override fun instantiateItem(container: ViewGroup?, position: Int): Any {
                container?.addView(m_listWeekExpectCover[position])
                return m_listWeekExpectCover[position]
            }
        }
        viewpager_weeklyexpect.apply {
            offscreenPageLimit = m_listWeekExpectCover.size
            pageMargin = 5
            setPageTransformer(false, MtimeViewPagerTransform())
        }
        weeklyexpect_container.setOnTouchListener { _, motionEvent ->
            viewpager_weeklyexpect.dispatchTouchEvent(motionEvent)
        }
    }

    private fun initChHighestRatingViews() {
        m_listChHignestRatingCover.forEachIndexed { index, imageView ->
            imageView.chAllDisplayImage(activity, m_listChHighestRating[index].posterUrl)
            imageView.setOnClickListener {

            }
            viewpager_highestrating_ch.apply {
                pageMargin = 5
                offscreenPageLimit = 10
                setPageTransformer(false, MtimeViewPagerTransform())
            }
            highestrating_container_ch.setOnTouchListener { v, event ->
                viewpager_highestrating_ch.dispatchTouchEvent(event)
            }
            viewpager_highestrating_ch.adapter = object : PagerAdapter() {
                override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
                    return view == `object`
                }

                override fun getCount(): Int {
                    return 10
                }

                override fun instantiateItem(container: ViewGroup?, position: Int): Any {
                    container?.addView(m_listChHignestRatingCover[position])
                    return m_listChHignestRatingCover[position]
                }

                override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
                    container?.removeView(m_listChHignestRatingCover[position])
                }
            }
        }

    }

    private fun requestWeeklyFocus() {
        NetWorkRealCallMtime.newInstance()
                .getRankListService()
                .requestTopList(2067)
                .chAllAsyncToMainThread()
                .subscribe({
                    m_listWeeklyFocus = it.movies
                    title_weeklyfocus.text = it.topList.summary
                    initWeeklyFocusViews()
                }, { t -> Log.e("neterror", t.toString()) })

    }

    private fun initWeeklyFocusViews() {
        m_listWeeklyFocusCover.forEachIndexed { index, view ->
            view.chAllDisplayImage(activity, m_listWeeklyFocus[index].posterUrl)
            view.setOnClickListener {

            }
        }
        viewpager_weeklyfocus.adapter = object : PagerAdapter() {

            override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
                return view == `object`
            }

            override fun getCount(): Int {
                return m_listWeeklyFocus.size
            }

            override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
                container?.removeView(m_listWeeklyFocusCover[position])
            }

            override fun instantiateItem(container: ViewGroup?, position: Int): Any {
                container?.addView(m_listWeeklyFocusCover[position])
                return m_listWeeklyFocusCover[position]
            }
        }
        viewpager_weeklyfocus.apply {
            offscreenPageLimit = m_listWeeklyFocus.size
            pageMargin = 5
            setPageTransformer(false, MtimeViewPagerTransform())
        }
        weeklyfocus_container.setOnTouchListener { _, motionEvent -> viewpager_weeklyfocus.dispatchTouchEvent(motionEvent) }
    }

    companion object {
        fun newInstance(): RankListFragment = RankListFragment()
    }
}