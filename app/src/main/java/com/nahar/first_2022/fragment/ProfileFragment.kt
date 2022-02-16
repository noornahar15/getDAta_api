package com.nahar.first_2022.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.nahar.first_2022.DashboardActivity
import com.nahar.first_2022.R
import com.nahar.first_2022.recycleView.MyAdapter
import com.nahar.first_2022.recycleView.News
import org.json.JSONObject


class ProfileFragment : Fragment() {
    private val newsList = ArrayList<News>()
    private lateinit var recyclerView: RecyclerView
    private var selectedItemId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        init(view)

        buildDisplayData()

        highlightedBottomNavigation()

        return view
    }

    private fun highlightedBottomNavigation() {
        if (R.id.navigationProfileId != selectedItemId) {
            setSelectedItemId(R.id.navigationProfileId)
        }
    }

    private fun init(view: View) {

        recyclerView = view.findViewById(R.id.recycleView)
        if (activity != null) {
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }

    }
    // api data call use volley method

    private fun buildDisplayData() {

        val queue = Volley.newRequestQueue(activity)
        val url = "https://reqres.in/api/users/2"

        val stringRequest = StringRequest(Request.Method.GET, url,

            { response ->

                        if (response.isNotEmpty()) {
                        }
                        val jsonObject = JSONObject(response)
                        val dataObject = jsonObject.getJSONObject("data")

//                        val jsonArray = jsonObjects.getJSONArray("data")

                        newsList.add(
                            News(
                                dataObject.getString("first_name")
                                        + " " + dataObject.getString("last_name"),
                                dataObject.getString("avatar")
                            )
                        )

                        recyclerView.adapter = activity?.let { MyAdapter(it, newsList) }

                        /*for (i in 0 until jsonArray.length()) {
                            val item = jsonArray.getJSONObject(i)

                            newsList.add(
                                News(
                                    item.getString("first_name")
                                            + " " + item.getString("last_name"),
                                    item.getString("avatar")
                                )
                            )

                            recyclerView.adapter = activity?.let { MyAdapter(it, newsList) }
                        }*/

            },

            { error ->
                Log.d("error", error.toString())
            })

        queue.add(stringRequest)
    }

    //item select option

    private fun setSelectedItemId(id: Int) {
        (activity as DashboardActivity).bottomNavigationView.selectedItemId = id
    }
}
