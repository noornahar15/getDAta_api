package com.nahar.first_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nahar.first_2022.fragment.ContactFragment
import com.nahar.first_2022.fragment.HomeFragment
import com.nahar.first_2022.fragment.ProfileFragment

@Suppress("DEPRECATION")
class DashboardActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    private var doubleBackToExitPressedOnce: Boolean? = false
    private var selectedItemId: Int? = null
    private var currentPage = 0
    private var homePage = 1
    private var profilePage = 2
    private var contactPage = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        bottomNavigation()
        setSelectedItemId(R.id.navigationHomeId)
    }


    private fun bottomNavigation() {
        bottomNavigationView = findViewById(R.id.btm_navigationViewId)
        bottomNavigationView.setOnNavigationItemSelectedListener(
            kOnNavigationItemSelectedListener
        )
    }

    private val kOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationHomeId -> {
                    if (currentPage != homePage) {
                        currentPage = homePage
                        addFragment(HomeFragment())
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigationProfileId ->{
                    if (currentPage != profilePage){
                        currentPage = profilePage
                        addFragment(ProfileFragment())
                    }
                    return@OnNavigationItemSelectedListener true
                }
                    R.id.navigationContactId->{
                        if(currentPage != contactPage){
                            currentPage = contactPage
                            addFragment(ContactFragment())
                        }
                        return@OnNavigationItemSelectedListener  true
                    }
            }
            false
        }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayoutId, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    override fun onBackPressed() {
        if (R.id.navigationHomeId != selectedItemId) {
            setSelectedItemId(R.id.navigationHomeId)

        } else {
            if (doubleBackToExitPressedOnce!!) {
                super.onBackPressed()
                return
            }
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "exit here", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
    }

    private fun setSelectedItemId(id: Int){
        bottomNavigationView.selectedItemId = id
    }
}


