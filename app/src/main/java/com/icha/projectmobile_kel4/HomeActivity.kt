package com.icha.projectmobile_kel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.icha.projectmobile_kel4.fragment.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var auth: FirebaseAuth;
    lateinit var homeFragment: HomeFragment
    lateinit var foodFragment: FoodFragment
    lateinit var reservasiFragment: ReservasiFragment
    lateinit var settingFragment: SettingFragment
    lateinit var logoutFragment: LogoutFragment
    lateinit var mapsFragment: MapsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        auth = FirebaseAuth.getInstance();

        setSupportActionBar(toolBar)
        val actionBar = supportActionBar
        actionBar?.title = "Navigation Drawer"

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            berandaUser,
            toolBar,
            (R.string.open),
            (R.string.close)
        ) {

        }

        drawerToggle.isDrawerIndicatorEnabled = true
        berandaUser.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        val navView: NavigationView = findViewById(R.id.nav_beranda)
        var headerView = navView.getHeaderView(0)
        var userNameTextView: TextView = headerView.findViewById(R.id.namaProfile)

        val user = auth.currentUser
        user?.let {

            val email = user.email
            userNameTextView.text = email
        }

        nav_beranda.setNavigationItemSelectedListener(this)

        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

    }

    override fun onNavigationItemSelected(MenuItem: MenuItem): Boolean {
        when (MenuItem.itemId) {
            R.id.home -> {
                homeFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.food -> {
                foodFragment = FoodFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, foodFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.reservasi -> {
                reservasiFragment = ReservasiFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, reservasiFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.maps -> {
                mapsFragment = MapsFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, mapsFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.setting -> {
                settingFragment = SettingFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, settingFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.logout -> {
                logoutFragment = LogoutFragment()
                supportFragmentManager
                auth.signOut();
                val intent = Intent(this, MainActivity::class.java);
                startActivity(intent)

            }
        }
        berandaUser.closeDrawer(GravityCompat.START)
        return true
    }
}
