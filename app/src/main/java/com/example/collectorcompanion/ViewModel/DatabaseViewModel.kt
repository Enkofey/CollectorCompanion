package com.example.collectorcompanion.ViewModel

import androidx.annotation.WorkerThread
import androidx.lifecycle.*
import com.example.collectorcompanion.Model.Entity.Brand
import com.example.collectorcompanion.Model.Repository.GlobalRepository
import com.example.collectorcompanion.Model.Entity.Collection
import com.example.collectorcompanion.Model.Entity.Objet
import com.example.collectorcompanion.Model.Entity.Type
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DatabaseViewModel(private val repository: GlobalRepository) : ViewModel() {

    /// Select All
    val allCollections: LiveData<List<Collection>> = repository.allCollections.asLiveData()
    val allTypes: LiveData<List<Type>> = repository.allTypes.asLiveData()
    val allObjets: LiveData<List<Objet>> = repository.allObjets.asLiveData()
    val allBrands: LiveData<List<Brand>> = repository.allBrands.asLiveData()

    /// Select where id
    var collectionWithId: LiveData<Collection>? = null
    var brandWithId: LiveData<Brand>? = null
    var objetWithId: LiveData<Objet>? = null
    var typeWithId: LiveData<Type>? = null

    fun getCollectionWithID(collectionID: Int) = viewModelScope.launch {
        collectionWithId = null
        collectionWithId = repository.getCollectionWithID(collectionID).asLiveData()
    }

    fun getBrandWithID(brandID: Int) = viewModelScope.launch {
        brandWithId = null
        brandWithId = repository.getBrandWithID(brandID).asLiveData()
    }

    fun getObjetWithID(objetID: Int) = viewModelScope.launch {
        objetWithId = null
        objetWithId = repository.getObjetWithID(objetID).asLiveData()
    }

    fun getTypeWithID(typeID: Int) = viewModelScope.launch {
        typeWithId = null
        typeWithId = repository.getTypeWithID(typeID).asLiveData()
    }
    /// Select where Attribute

    /* Collection */
    var collectionWithName : LiveData<List<Collection>>? = null
    fun getCollectionWithName(collectionName: String)= viewModelScope.launch {
        collectionWithName = null
        collectionWithName = repository.getCollectionWithName(collectionName).asLiveData()
    }

    /* Brand */
    var brandWithName : LiveData<List<Brand>>? = null
    fun getBrandWithName(brandName: String)= viewModelScope.launch {
        brandWithName = null
        brandWithName = repository.getBrandWithName(brandName).asLiveData()
    }

    var brandWithTypeId : LiveData<List<Brand>>? = null
    fun getBrandWithTypeID(typeID: Int)= viewModelScope.launch {
        brandWithTypeId = null
        brandWithTypeId = repository.getBrandWithTypeID(typeID).asLiveData()
    }

    /* Objet */
    var objetWithName : LiveData<List<Objet>>? = null
    var objetWithYear : LiveData<List<Objet>>? = null
    var objetWithTypeId : LiveData<List<Objet>>? = null
    var objetWithBrandId : LiveData<List<Objet>>? = null
    var objetWithCollectionId : LiveData<List<Objet>>? = null

    fun getObjetWithName(objetName: String)= viewModelScope.launch {
        objetWithName = null
        objetWithName = repository.getObjetWithName(objetName).asLiveData()
    }

    fun getObjetWithYear(objetYear: Int)= viewModelScope.launch {
        objetWithYear = null
        objetWithYear = repository.getObjetWithYear(objetYear).asLiveData()
    }

    fun getObjetWithTypeID(typeID: Int)= viewModelScope.launch {
        objetWithTypeId = null
        objetWithTypeId = repository.getObjetWithTypeID(typeID).asLiveData()
    }

    fun getObjetWithCollectionID(collectionID: Int)= viewModelScope.launch {
        objetWithCollectionId = null
        objetWithCollectionId = repository.getObjetWithCollectionID(collectionID).asLiveData()
    }

    fun getObjetWithBrandID(brandID: Int)= viewModelScope.launch {
        objetWithBrandId = null
        objetWithBrandId = repository.getObjetWithBrandID(brandID).asLiveData()
    }

    /* Type */
    var typeWithName : LiveData<List<Type>>? = null
    fun getTypeWithName(typeName: String)= viewModelScope.launch {
        typeWithName = null
        typeWithName = repository.getTypeWithName(typeName).asLiveData()
    }
    var typeWithBrandId : LiveData<List<Type>>? = null
    fun getTypeWithBrandID(brandID: Int)= viewModelScope.launch {
        typeWithBrandId = null
        typeWithBrandId = repository.getTypeWithBrandID(brandID).asLiveData()
    }

    /// Insert
    fun insert(collection: Collection) = viewModelScope.launch {
        repository.insert(collection)
    }

    fun insert(type: Type) = viewModelScope.launch {
        repository.insert(type)
    }

    fun insert(objet: Objet) = viewModelScope.launch {
        repository.insert(objet)
    }

    fun insert(brand: Brand) = viewModelScope.launch {
        repository.insert(brand)
    }

    /// Delete All
    fun deleteAllCollection() = viewModelScope.launch {
        repository.deleteAllCollection()
    }
    fun deleteAllObjet() = viewModelScope.launch {
        repository.deleteAllObjet()
    }
    fun deleteAllBrand() = viewModelScope.launch {
        repository.deleteAllBrand()
    }
    fun deleteAllType() = viewModelScope.launch {
        repository.deleteAllType()
    }

    /// Delete Where

    fun deleteCollection(collectionID: Int) = viewModelScope.launch {
        repository.deleteCollection(collectionID)
    }
    fun deleteObjet(objetID: Int) = viewModelScope.launch {
        repository.deleteObjet(objetID)
    }
    fun deleteBrand(brandID: Int) = viewModelScope.launch {
        repository.deleteBrand(brandID)
    }
    fun deleteType(typeID: Int) = viewModelScope.launch {
        repository.deleteType(typeID)
    }

    /// Update
    fun updateCollection(collection: Collection) = viewModelScope.launch {
        repository.updateCollection(collection)
    }
    fun updateObjet(objet: Objet) = viewModelScope.launch {
        repository.updateObjet(objet)
    }
    fun updateBrand(brand: Brand) = viewModelScope.launch {
        repository.updateBrand(brand)
    }
    fun updateType(type: Type) = viewModelScope.launch {
        repository.updateType(type)
    }
}

class DatabaseViewModelFactory(private val repository: GlobalRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DatabaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DatabaseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}