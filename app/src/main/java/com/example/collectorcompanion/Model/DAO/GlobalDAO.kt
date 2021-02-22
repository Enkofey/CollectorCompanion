package com.example.collectorcompanion.Model.DAO

import androidx.room.*
import com.example.collectorcompanion.Model.Entity.Brand
import com.example.collectorcompanion.Model.Entity.Collection
import com.example.collectorcompanion.Model.Entity.Objet
import com.example.collectorcompanion.Model.Entity.Type
import kotlinx.coroutines.flow.Flow

@Dao()

interface GlobalDAO {

    /// Select All
    @Query("SELECT * FROM Collection")
    fun getCollections(): Flow<List<Collection>>

    @Query("SELECT * FROM Brand")
    fun getBrands(): Flow<List<Brand>>

    @Query("SELECT * FROM Objet")
    fun getObjets(): Flow<List<Objet>>

    @Query("SELECT * FROM Type")
    fun getTypes(): Flow<List<Type>>

    /// Select Where id

    @Query("SELECT * FROM Collection c WHERE c.idCollection=:collectionID")
    fun getCollectionWithID(collectionID : Int): Flow<Collection>
    @Query("SELECT * FROM Brand b WHERE b.idBrand=:brandID")
    fun getBrandWithID(brandID : Int): Flow<Brand>
    @Query("SELECT * FROM Objet o WHERE o.idObjet=:objetID")
    fun getObjetWithID(objetID : Int): Flow<Objet>
    @Query("SELECT * FROM Type t WHERE t.idType=:typeID")
    fun getTypeWithID(typeID : Int): Flow<Type>

    /// Select Where Attribute

    /* Collection */

    @Query("SELECT * FROM Collection c WHERE c.collection_name=:collectionName")
    fun getCollectionWithName(collectionName : String): Flow<List<Collection>>

    /* Brand */

    @Query("SELECT * FROM Brand d WHERE d.brand_name=:brandName")
    fun getBrandWithName(brandName : String): Flow<List<Brand>>
    @Query("SELECT * FROM Brand b WHERE b.idType=:typeID")
    fun getBrandWithTypeID(typeID : Int): Flow<List<Brand>>

    /* Objet */

    @Query("SELECT * FROM Objet o WHERE o.objet_name=:objetName")
    fun getObjetWithName(objetName : String): Flow<List<Objet>>

    @Query("SELECT * FROM Objet o WHERE o.objet_year=:objetYear")
    fun getObjetWithYear(objetYear : Int): Flow<List<Objet>>

    @Query("SELECT * FROM Objet o WHERE o.idType=:typeID")
    fun getObjetWithTypeID(typeID : Int): Flow<List<Objet>>

    @Query("SELECT * FROM Objet o WHERE o.idCollection=:collectionID")
    fun getObjetWithCollectionID(collectionID : Int): Flow<List<Objet>>

    @Query("SELECT * FROM Objet o WHERE o.idBrand=:brandID")
    fun getObjetWithBrandID(brandID : Int): Flow<List<Objet>>

    /* Type */

    @Query("SELECT * FROM Type t WHERE t.type_name=:typeName")
    fun getTypeWithName(typeName : String): Flow<List<Type>>
    @Query("SELECT * FROM Type t WHERE t.idBrand=:brandID")
    fun getTypeWithBrandID(brandID : Int): Flow<List<Type>>

    /// Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(collection: Collection)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(brand: Brand)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(objet: Objet)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(type: Type)

    /// Delete All
    @Query("DELETE FROM Collection")
    suspend fun deleteAllCollection()

    @Query("DELETE FROM Type")
    suspend fun deleteAllType()

    @Query("DELETE FROM Objet")
    suspend fun deleteAllObjet()

    @Query("DELETE FROM Brand")
    suspend fun deleteAllBrand()

    /// Delete where

    @Query("DELETE FROM Collection WHERE Collection.idCollection=:collectionID")
    suspend fun deleteCollection(collectionID : Int)

    @Query("DELETE FROM Type WHERE Type.idType=:typeID")
    suspend fun deleteType(typeID : Int)

    @Query("DELETE FROM Objet WHERE Objet.idObjet=:objetID")
    suspend fun deleteObjet(objetID : Int)

    @Query("DELETE FROM Brand WHERE Brand.idBrand=:brandID")
    suspend fun deleteBrand(brandID : Int)

    /// Update

    @Update
    suspend fun updateCollection(collection : Collection)
    @Update
    suspend fun updateBrand(brand : Brand)
    @Update
    suspend fun updateObjet(objet : Objet)
    @Update
    suspend fun updateType(type : Type)

}