package com.nahar.first_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private var threadTimeOut: Long = 3000
    private var email: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setTheme(R.style.SplashTheme)
        setContentView(R.layout.activity_main)

        initialization()
       /* setProgressBarColor()*/
        splashTimeOut()
        getSharedPrefValue()
    }

    private fun getSharedPrefValue(){
        email = Utility.customSharedPref(this, "loginInfo")
            .getString("email", "").toString()
    }
   /* private fun setProgressBarColor() {
        progressBar.indeterminateDrawable
            .setColorFilter(
                resources.getColor(R.color.black),
                PorterDuff.Mode.MULTIPLY
            )
    }*/

    private fun splashTimeOut() {
        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(threadTimeOut)
                    if (email.isNotEmpty()) {

                        startActivity(Intent(this@MainActivity, DashboardActivity::class.java))
                        finish()

                    } else {
                        startActivity(Intent(this@MainActivity, PostDataActivity::class.java))
                        finish()
                    }
                    progressBar.visibility = View.GONE
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        background.start()
    }

    private fun initialization() {
        progressBar = findViewById(R.id.progress_bar)
    }
}





