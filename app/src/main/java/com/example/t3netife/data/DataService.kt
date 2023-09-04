package com.example.t3netife.data

import com.example.t3netife.data.DataClass
import retrofit2.Call
import retrofit2.http.GET

interface DataService {
    @GET("gifs/trending?api_key=No9BcLZ4JWbQ994B5DZwZ96DT02KeNL4")
    fun getGifs(): Call<DataClass>
}