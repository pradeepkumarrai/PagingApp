package com.pradeep.navigation.Network

import com.pradeep.navigation.DataClass.RickAndMorty
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("api/character")
    fun getStackList(@Query("page") query: Int): Call<RickAndMorty>

    @GET("api/character")
    suspend fun getDataFromAPI(@Query("page") query: Int):RickAndMorty
}