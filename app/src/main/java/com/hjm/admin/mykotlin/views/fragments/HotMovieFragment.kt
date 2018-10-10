package com.hjm.admin.mykotlin.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hjm.admin.mykotlin.R
import kotlinx.android.synthetic.main.fragment_hot_movie.*

/**
 * Created by admin on 2017/10/24.
 */
class HotMovieFragment : Fragment() {
    val mArrTitles: ArrayList<String>by lazy { arrayListOf("正在热映", "即将热映", "本地影院") }
    val mArrFragments: List<Fragment>by lazy { arrayListOf(HotMovieNowAdaysFragment.newInstance(), HotMovieSoonComeFragment.newInstance(), HotMovie_CinemasFragment.newInstance()) }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_hot_movie, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabLayout()
    }

    private fun initTabLayout() {
        hotMovieViewPager.offscreenPageLimit = 3
        hotMovieViewPager.adapter = object : FragmentStatePagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment = mArrFragments[position]
            override fun getCount(): Int = mArrFragments.size
            override fun getPageTitle(position: Int): CharSequence = mArrTitles[position]
        }
        hotMovieTabLayout.setupWithViewPager(hotMovieViewPager)
        hotMovieViewPager.currentItem = 1
    }

    companion object {
        fun newInstance(): HotMovieFragment = HotMovieFragment()
    }
}