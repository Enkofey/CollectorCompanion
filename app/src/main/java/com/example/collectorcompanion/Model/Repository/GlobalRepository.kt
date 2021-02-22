package com.example.collectorcompanion.Model.Repository

import androidx.annotation.WorkerThread
import androidx.room.Update
import com.example.collectorcompanion.Model.DAO.GlobalDAO
import com.example.collectorcompanion.Model.Entity.Brand
import com.example.collectorcompanion.Model.Entity.Collection
import com.example.collectorcompanion.Model.Entity.Objet
import com.example.collectorcompanion.Model.Entity.Type
import kotlinx.coroutines.flow.Flow

class GlobalRepository(private val globalDAO: GlobalDAO) {

    /// Select All
    val allCollections: Flow<List<Collection>> = globalDAO.getCollections()
    val allTypes: Flow<List<Type>> = globalDAO.getTypes()
    val allObjets: Flow<List<Objet>> = globalDAO.getObjets()
    val allBrands: Flow<List<Brand>> = globalDAO.getBrands()

    /// Select where id
    fun getCollectionWithID(collectionID : Int): Flow<Collection>{
        return globalDAO.getCollectionWithID(collectionID)
    }
    fun getBrandWithID(brandID : Int): Flow<Brand>{
        return globalDAO.getBrandWithID(brandID)
    }
    fun getObjetWithID(objetID : Int): Flow<Objet>{
        return globalDAO.getObjetWithID(objetID)
    }
    fun getTypeWithID(typeID : Int): Flow<Type>{
        return globalDAO.getTypeWithID(typeID)
    }
    /// Select where Attribute

    /* Collection */

    fun getCollectionWithName(collectionName : String): Flow<List<Collection>>{
        return globalDAO.getCollectionWithName(collectionName)
    }

    /* Brand */

    fun getBrandWithName(brandName : String): Flow<List<Brand>>{
        return globalDAO.getBrandWithName(brandName)
    }
    fun getBrandWithTypeID(typeID : Int): Flow<List<Brand>>{
        return globalDAO.getBrandWithTypeID(typeID)
    }

    /* Objet */

    fun getObjetWithName(objetName : String): Flow<List<Objet>>{
        return globalDAO.getObjetWithName(objetName)
    }
    fun getObjetWithYear(objetYear : Int): Flow<List<Objet>>{
        return globalDAO.getObjetWithYear(objetYear)
    }
    fun getObjetWithTypeID(typeID : Int): Flow<List<Objet>>{
        return globalDAO.getObjetWithTypeID(typeID)
    }
    fun getObjetWithCollectionID(collectionID : Int): Flow<List<Objet>>{
        return globalDAO.getObjetWithCollectionID(collectionID)
    }
    fun getObjetWithBrandID(brandID : Int): Flow<List<Objet>>{
        return globalDAO.getObjetWithBrandID(brandID)
    }

    /* Type */

    fun getTypeWithName(typeName : String): Flow<List<Type>>{
        return globalDAO.getTypeWithName(typeName)
    }
    fun getTypeWithBrandID(brandID : Int): Flow<List<Type>>{
        return globalDAO.getTypeWithBrandID(brandID)
    }

    /// Insert
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(collection: Collection) {
        globalDAO.insert(collection)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(type: Type) {
        globalDAO.insert(type)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(objet: Objet) {
        globalDAO.insert(objet)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(brand: Brand) {
        globalDAO.insert(brand)
    }

    /// Delete All
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllCollection() {
        globalDAO.deleteAllCollection()
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllType() {
        globalDAO.deleteAllType()
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllObjet() {
        globalDAO.deleteAllObjet()
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllBrand() {
        globalDAO.deleteAllBrand()
    }
    /// Delete where
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteCollection(collectionID : Int){
        globalDAO.deleteCollection(collectionID)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteType(typeID : Int){
        globalDAO.deleteType(typeID)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteObjet(objetID : Int){
        globalDAO.deleteObjet(objetID)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteBrand(brandID : Int){
        globalDAO.deleteBrand(brandID)
    }
    /// Update
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateCollection(collection : Collection){
        globalDAO.updateCollection(collection)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateBrand(brand : Brand){
        globalDAO.updateBrand(brand)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateObjet(objet : Objet){
        globalDAO.updateObjet(objet)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateType(type : Type){
        globalDAO.updateType(type)
    }
}
