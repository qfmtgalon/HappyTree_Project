package com.example.happytree.API

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private val retrofit = Retrofit.Builder().baseUrl("https://fastapi-production-0f7f.up.railway.app/").addConverterFactory(GsonConverterFactory.create()).build()

interface DiseaseService {
    @GET("anthracnose/")
    fun getAnthracnoseData():
            Call<DiseaseResponse>

    @GET("redrust/")
    fun getRedData():
            Call<DiseaseResponse>

    @GET("sootymold/")
    fun getSootyData():
            Call<DiseaseResponse>
}

object diseaseSignal{
    val retrofitService: DiseaseService by lazy {
        retrofit.create(DiseaseService::class.java) }
}