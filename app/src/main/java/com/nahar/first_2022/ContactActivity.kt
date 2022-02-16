package com.nahar.first_2022

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ContactActivity : AppCompatActivity() {
    private var contact: Boolean = false
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        getIntentValue()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getIntentValue() {
        contact = intent.getBooleanExtra("contact", false)

        val fullName: TextView = findViewById(R.id.nameTv)

        val image: CircleImageView = findViewById(R.id.image_view)

        val bundle: Bundle? = intent.extras
        val name = bundle!!.getString("name")
        val imageId = bundle.getString("imageId")

        fullName.text = name

        Picasso
            .get()
            .load(imageId)
            .placeholder(R.drawable.b)
            .into(image)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
