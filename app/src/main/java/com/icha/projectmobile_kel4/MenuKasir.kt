package com.icha.projectmobile_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.icha.projectmobile_kel4.kasirfragment.BerandaKasirFragment
import com.icha.projectmobile_kel4.kasirfragment.ListMenuKasirFragment
import com.icha.projectmobile_kel4.kasirfragment.ReservasiKasirFragment
import kotlinx.android.synthetic.main.menu_kasir.*

class MenuKasir  : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var berandaKasirFragment: BerandaKasirFragment
    lateinit var reservasiKasirFragment: ReservasiKasirFragment
    lateinit var listMenuKasirFragment: ListMenuKasirFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_kasir)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open,
            R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.home -> {
                berandaKasirFragment = berandaKasirFragment
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, berandaKasirFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
                Toast.makeText(applicationContext, "Beranda", Toast.LENGTH_SHORT)
                    .show()
            }

            R.id.listmenu -> {
                listMenuKasirFragment = ListMenuKasirFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, listMenuKasirFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
                Toast.makeText(applicationContext, "List Menu", Toast.LENGTH_SHORT)
                    .show()
            }

            R.id.reservationmenu -> {
                reservasiKasirFragment = ReservasiKasirFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, reservasiKasirFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
                Toast.makeText(applicationContext, "List Reservation", Toast.LENGTH_SHORT)
                    .show()
            }

            R.id.logoutmenu -> {
                setContentView(R.layout.login)
            }
        }
        drawerLayout.closeDrawers()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}