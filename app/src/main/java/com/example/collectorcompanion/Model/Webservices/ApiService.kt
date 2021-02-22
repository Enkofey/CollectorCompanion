package com.example.collectorcompanion.Model.Webservices

import androidx.annotation.WorkerThread
import com.example.collectorcompanion.BuildConfig
import com.example.collectorcompanion.Model.Entity.Collection
import com.example.collectorcompanion.Model.Entity.Objet
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    val BASE_URL = BuildConfig.url_server
    var gson : Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").create()
    var retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()

    val collectorApi: ApiEndpoint = retrofit.create(ApiEndpoint::class.java)

    //suspend fun getObjets(): List<Objet> = retrofit.create(ApiEndpoint::class.java).getObjets()

    //suspend fun getObjet(objetName : String): List<Objet> {
    //    return retrofit.create(ApiEndpoint::class.java).getObjet(objetName)
    //}
}