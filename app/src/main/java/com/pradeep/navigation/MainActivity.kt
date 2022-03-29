package com.pradeep.navigation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.pradeep.navigation.Fragments.HomeFragment
import com.pradeep.navigation.Fragments.ProfileFragments
import com.pradeep.navigation.Fragments.SavingFragment
import kotlinx.android.synthetic.main.main_layout.*

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val profileFragment = ProfileFragments()
    private val savingFragment = SavingFragment()
    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        setCurrentFragment(homeFragment)

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.homeDrawer -> {
                    setCurrentFragment(homeFragment)
                }
                R.id.savingDrawer -> {
                    setCurrentFragment(savingFragment)
                }
                R.id.profileDrawer -> {
                    setCurrentFragment(profileFragment)
                }
                R.id.PagingDrawer -> {
                    val intent = Intent(this, PagingActivity::class.java)
                    startActivity(intent)
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeMenu -> setCurrentFragment(homeFragment)
                R.id.profileMenu -> setCurrentFragment(profileFragment)
                R.id.savingMenu -> setCurrentFragment(savingFragment)
            }
            true
        }

    }


    private fun setCurrentFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFrameLayout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}