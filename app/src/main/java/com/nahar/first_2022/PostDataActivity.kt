package com.nahar.first_2022


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class PostDataActivity : AppCompatActivity() {
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var postBtn: Button
    private lateinit var stringRequest: StringRequest
    private lateinit var progressBar: ProgressBar
    private var email: String = ""
    private var password: String = ""
    private var requestLocationCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_data)

         checkPermission()
              initialization()
              buttonBtClickListener()
              onClick()

        emailEt = findViewById(R.id.email_et)
        postBtn = findViewById(R.id.post_btn)

    }

    private fun apiCall() {

        val queue = Volley.newRequestQueue(this)
        val url = "https://reqres.in/api/users?page=2"


        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val jsonObject = JSONObject(response)
                val jsonArray = jsonObject.getJSONArray("data")

                for( i in 0 until jsonArray.length()){
                    val item = jsonArray.getJSONObject(i)
                    Log.d("successResponse", item.getString("email"))

                    dashBoardActivity()
                }

                Log.d("successResponse", jsonArray.toString())

            },
            { error -> Log.d("errorResponse", error.toString()) })

        queue.add(stringRequest)

    }



    private fun onClick() {
        buttonBtClickListener()
    }

    private fun buttonBtClickListener() {
        postBtn.setOnClickListener {
            apiCall()

        }
    }

   /* private fun loginValidation() {
        email = emailEt.text.toString()
        password = passwordEt.text.toString()

        if (email.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Please enter name or email", Toast.LENGTH_SHORT).show()

        } else if (email.isEmpty()) {
            Toast.makeText(this, "Please enter  email", Toast.LENGTH_SHORT).show()

        } else if (password.isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
        } else {
            dashBoardActivity()
           *//* sendLoginData(email, password)*//*
        }
    }*/

    private fun dashBoardActivity(){
        startActivity(
            Intent(this, DashboardActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

 /*   private fun sendLoginData(email: String, password: String) {
        progressBar.visibility = View.VISIBLE
        postBtn.isEnabled = false
        RetrofitClient.instance.postData(User(email, password))
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        progressBar.visibility = View.GONE
                        setLoginData(response, email)

                        Toast.makeText(
                            this@PostData, "Login successful",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        progressBar.visibility = View.GONE
                        postBtn.isEnabled = true

                        Toast.makeText(
                            this@PostData, "" + response.errorBody()!!.string(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }*/

    /*private fun setLoginData(response: Response<User>, email: String) {
        startActivity(
            Intent(this@PostData, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
        Utility.customSharedPref(this@PostData, "loginInfo")
            .edit()
            .putString("token", response.body()!!.token)
            .putString("email", email)
            .apply()

    }*/

    private fun initialization() {
        passwordEt = findViewById(R.id.password_et)
        emailEt = findViewById(R.id.email_et)
        postBtn = findViewById(R.id.post_btn)
        progressBar = findViewById(R.id.progress_bar)
    }
// app permission
    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
               Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE,
                ),
                requestLocationCode
            )
            return
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestLocationCode && grantResults.isNotEmpty()) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this,
                    getText(R.string.permission_denied),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
