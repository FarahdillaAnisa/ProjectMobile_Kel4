package com.icha.projectmobile_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.icha.projectmobile_kel4.menufragment.MakananFragment
import com.icha.projectmobile_kel4.menufragment.MinumanFragment
import com.icha.projectmobile_kel4.menufragment.CemilanFragment
import com.google.android.material.tabs.TabLayout
import java.text.FieldPosition

class HalamanMenu  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_menu)

        val toolbar: Toolbar = findViewById(R.id.toolbar_awal)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""

        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val viewPagerAdapter =  ViewPagerAdapter(supportFragmentManager)

        viewPagerAdapter.tambahFragment(MakananFragment(),  "Makanan")
        viewPagerAdapter.tambahFragment(MinumanFragment(), "Minuman")
        viewPagerAdapter.tambahFragment(CemilanFragment(), "Cemilan")

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }
    internal class ViewPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(
        fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

        private val fragments: ArrayList<Fragment>
        private val juduls: ArrayList<String>

        init {
            fragments = ArrayList()
            juduls = ArrayList()
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment{
            return fragments[position]
        }

        fun tambahFragment(fragment: Fragment, judul: String){
            fragments.add(fragment)
            juduls.add(judul)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return juduls[position]
        }
    }
}