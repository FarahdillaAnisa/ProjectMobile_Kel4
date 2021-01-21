package com.icha.projectmobile_kel4.kasirfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.icha.projectmobile_kel4.HalamanMenu
import com.icha.projectmobile_kel4.R
import com.icha.projectmobile_kel4.menufragment.CemilanFragment
import com.icha.projectmobile_kel4.menufragment.MakananFragment
import com.icha.projectmobile_kel4.menufragment.MinumanFragment

class ListMenuKasirFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_menu_kasir, container, false)
    }


}