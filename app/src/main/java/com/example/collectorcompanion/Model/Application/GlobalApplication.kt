package com.example.collectorcompanion.Model.Application

import android.app.Application
import com.example.collectorcompanion.Model.Database.GlobalRoomDatabase
import com.example.collectorcompanion.Model.Repository.GlobalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalRoomDatabase.getDatabase(this,applicationScope)
    }

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { GlobalRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { GlobalRepository(database.collectionDao())}
}