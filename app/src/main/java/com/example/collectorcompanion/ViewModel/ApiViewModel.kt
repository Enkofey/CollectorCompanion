package com.example.collectorcompanion.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.collectorcompanion.Model.Entity.Objet
import com.example.collectorcompanion.Model.Repository.GlobalRepository
import com.example.collectorcompanion.Model.Webservices.ApiEndpoint
import com.example.collectorcompanion.Model.Webservices.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

object ApiViewModel : ViewModel() {

    var objet : MutableLiveData<List<Objet>> = MutableLiveData()
    var objets : MutableLiveData<List<Objet>> = MutableLiveData()

    fun getObjets(){
        val objetsCall = ApiService.collectorApi.getObjets()
        objetsCall.enqueue(object : Callback<List<Objet>> {
            override fun onResponse(call: Call<List<Objet>>, response: Response<List<Objet>>) {
                objets.value = response.body()
            }

            override fun onFailure(call: Call<List<Objet>>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }


    //fun getObjets()  = viewModelScope.launch {
        //objets = null
        //objets = ApiService.getObjets().
    //}
    //fun getObjet(objetName : String) = viewModelScope.launch{
    //    objet = null
    //    objet = ApiService.getObjet(objetName).asLiveData()
    //}
}