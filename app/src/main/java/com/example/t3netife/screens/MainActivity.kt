package com.example.t3netife.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.t3netife.R
import com.example.t3netife.adapter.GifsAdapter
import com.example.t3netife.data.DataClass
import com.example.t3netife.data.DataObject
import com.example.t3netife.data.DataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.giphy.com/v1/"
const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val gifs = mutableListOf<DataObject>()
        val adapter = GifsAdapter(this, gifs)

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        adapter.setOnItemClickListener(object : GifsAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("url", gifs[position].images.ogImage.url)
                startActivity(intent)
            }

        })
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(DataService::class.java)
        retrofitService.getGifs().enqueue(object : Callback<DataClass?>{
            override fun onResponse(call: Call<DataClass?>, response: Response<DataClass?>) {
                val body = response.body()
                if (body == null){
                    Log.d(TAG, "onResponse: No response")
                }
                gifs.addAll(body!!.res)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<DataClass?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}



