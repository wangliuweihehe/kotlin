package com.hjm.admin.mykotlin.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hjm.admin.mykotlin.R
import kotlinx.android.synthetic.main.fragment_find_funny.*

/**
 * Created by admin on 2017/11/3.
 */
class FindFunnyFragment : Fragment() {
    val m_listFragments: List<Fragment> by lazy {
        arrayListOf(FindFunny_NewsFragment.newIntance(), FindFunny_TrailersFragment.newInstance(), FindFunny_RiewFragment.newInstance())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_find_funny, container, false)
    }
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val arrTitleNames = arrayListOf<String>("MTIME新闻", "MTIME预告", "MTIME影评")
        findfunny_viewpager.adapter = object : FragmentStatePagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return m_listFragments[position]
            }
            override fun getCount(): Int {
                return m_listFragments.size
            }
            override fun getPageTitle(position: Int): CharSequence {
                return arrTitleNames[position]
            }
        }
        findfunny_viewpager.offscreenPageLimit = 3
        findfunny_tablayout.setupWithViewPager(findfunny_viewpager)
    }
    companion object {
        fun newInstance(): FindFunnyFragment = FindFunnyFragment()
    }
}