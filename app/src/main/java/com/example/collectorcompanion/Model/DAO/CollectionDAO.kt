package com.example.collectorcompanion.Model.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.collectorcompanion.Model.Entity.Collection
import kotlinx.coroutines.flow.Flow

@Dao()

interface CollectionDAO {

    @Query("SELECT * FROM Collection")
    fun getCollections(): Flow<List<Collection>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(collection: Collection)

    @Query("DELETE FROM Collection")
    suspend fun deleteAll()


}