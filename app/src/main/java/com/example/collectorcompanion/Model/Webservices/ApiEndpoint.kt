package com.example.collectorcompanion.Model.Webservices

import androidx.lifecycle.LiveData
import com.example.collectorcompanion.Model.Entity.Objet
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndpoint {
    @GET("objet/")
    fun getObjets(): Call<List<Objet>>

    @GET("objet/{nameObjet}")
    fun getObjet(@Path("nameObjet") nameObjet :String?): Call<List<Objet>>
}