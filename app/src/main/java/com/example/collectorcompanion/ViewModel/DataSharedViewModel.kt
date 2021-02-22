package com.example.collectorcompanion.ViewModel

import androidx.lifecycle.ViewModel
import com.example.collectorcompanion.Model.Entity.Collection
import com.example.collectorcompanion.Model.Entity.Objet

class DataSharedViewModel : ViewModel(){
    /// Collection Fragment
    var newCollection : Collection? = null
    var IsANewCollection : Boolean = false
    var modifyCollection : Collection? = null
    var IsAModifyCollection : Boolean = false
    fun SetNewCollection(collection : Collection){
        newCollection = collection
    }
    fun SetIsANewCollection(state : Boolean){
        IsANewCollection = state
    }
    fun SetModifyCollection(collection : Collection){
        modifyCollection = collection
    }
    fun SetIsAModifyCollection(state : Boolean){
        IsAModifyCollection = state
    }

    /// Object Fragment
    var idCollectionCurrent : Int? = null
    var newObjet : Objet? = null
    var IsANewObjet : Boolean = false
    var modifyObjet : Objet? = null
    var IsAModifyObjet : Boolean = false
    fun SetNewObjet(objet : Objet){
        newObjet = objet
    }
    fun SetIsANewObjet(state : Boolean){
        IsANewObjet = state
    }
    fun SetModifyObjet(objet : Objet){
        modifyObjet = objet
    }
    fun SetIsAModifyObjet(state : Boolean){
        IsAModifyObjet = state
    }
}