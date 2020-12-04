package com.example.collectorcompanion.Model.Application

import android.app.Application
import com.example.collectorcompanion.Model.Database.ApplicationRoomDatabase
import com.example.collectorcompanion.Model.Repository.ApplicationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class GlobalApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ApplicationRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ApplicationRepository(database.collectionDao())}
}