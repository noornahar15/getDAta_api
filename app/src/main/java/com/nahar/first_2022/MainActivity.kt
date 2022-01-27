package com.nahar.first_2022


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.String as String

class MainActivity : AppCompatActivity() {

    private lateinit var  nameEt: TextInputEditText
    private var name: String = ""

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val listview = findViewById<ListView>(R.id.listview)
         /*val name = findViewById<String>(R.id.nameTv)*/

            val rf = Retrofit.Builder()
                .baseUrl(RetrofitInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

          var API : RetrofitInterface = rf.create(RetrofitInterface::class.java)
          var call: Call<List<PostModel?>?>? = API.posts

        call?.enqueue(object : Callback<List<PostModel?>?>{
            override fun onFailure(call: Call<List<PostModel?>?>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<List<PostModel?>?>,
                response: Response<List<PostModel?>?>
            ) {
                Log.d("apiData", "onSuccess " + response)

                var postlist: List<PostModel>? = response.body() as List<PostModel>
                var post: Array<String?> = arrayOfNulls<String>(postlist!!.size)

                for (i in postlist!!.indices)
                    post[i] = postlist!![i]!!.title

                var adapter = ArrayAdapter<String>(applicationContext,android.R.layout.simple_dropdown_item_1line,post)
                listview.adapter = adapter

            }

        })

    }


}



