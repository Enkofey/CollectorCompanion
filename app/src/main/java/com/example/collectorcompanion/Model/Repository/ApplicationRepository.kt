package com.example.collectorcompanion.Model.Repository

import androidx.annotation.WorkerThread
import com.example.collectorcompanion.Model.DAO.CollectionDAO
import com.example.collectorcompanion.Model.Entity.Collection
import kotlinx.coroutines.flow.Flow

class ApplicationRepository(private val collectionDAO: CollectionDAO) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allCollections: Flow<List<Collection>> = collectionDAO.getCollections()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(collection: Collection) {
        collectionDAO.insert(collection)
    }
}
