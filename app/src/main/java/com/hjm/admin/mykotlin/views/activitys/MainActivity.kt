package com.hjm.admin.mykotlin.views.activitys

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import com.hjm.admin.mykotlin.R
import com.hjm.admin.mykotlin.views.fragments.FindFunnyFragment
import com.hjm.admin.mykotlin.views.fragments.HotMovieFragment
import com.hjm.admin.mykotlin.views.fragments.RankListFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by admin on 2017/10/24.
 */
class MainActivity : AppCompatActivity() {
    var mCurrentIndex: Int = 0
    val m_listFragments: List<Fragment> by lazy { arrayListOf(HotMovieFragment.newInstance(), FindFunnyFragment.newInstance(), RankListFragment.newInstance()) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBarDrawer()
        swtchDisplayFragment(0)
        navigation_main.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.drawer_menu_1 -> swtchDisplayFragment(0)
                R.id.drawer_menu_2 -> swtchDisplayFragment(1)
                R.id.drawer_menu_3 -> swtchDisplayFragment(2)
            }
            drawer.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
        navigation_main.setItemTextAppearance(R.style.SnackbarTextStyle)
        navigation_main.itemIconTintList = null//使navigation中的icon保持本来的颜色
    }


    lateinit var toggle: ActionBarDrawerToggle

    fun setToolBarDrawer() {
        toggle = ActionBarDrawerToggle(this, drawer, toolBar, 0, 0)
        drawer.addDrawerListener(toggle)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
        toolBar.setNavigationOnClickListener {
            if (!drawer.isDrawerVisible(GravityCompat.START))
                drawer.openDrawer(GravityCompat.START)
            else
                drawer.closeDrawer(GravityCompat.START)
        }
    }

    fun swtchDisplayFragment(nDisplayIndex: Int) {
        val mgr = supportFragmentManager.beginTransaction()
        if (mCurrentIndex == nDisplayIndex) {
            if (!m_listFragments[nDisplayIndex].isAdded)
                mgr.add(R.id.container_all, m_listFragments[nDisplayIndex]).show(m_listFragments[nDisplayIndex])
        } else {
            if (m_listFragments[nDisplayIndex].isAdded) {
                mgr.hide(m_listFragments[mCurrentIndex]).show(m_listFragments[nDisplayIndex])
            } else {
                mgr.hide(m_listFragments[mCurrentIndex]).add(R.id.container_all, m_listFragments[nDisplayIndex]).show(m_listFragments[nDisplayIndex])
            }
        }
        mgr.commit()
        mCurrentIndex = nDisplayIndex
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.maintoolbar, menu)
        val searchItem = menu?.findItem(R.id.main_action_find)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.queryHint = "输入电影名查找"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                startActivity(Intent(this@MainActivity, SearchResultActivity::class.java))
                return true
            }

        })
        return true
    }
}