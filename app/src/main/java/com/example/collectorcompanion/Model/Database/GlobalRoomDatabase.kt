package com.example.collectorcompanion.Model.Database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.collectorcompanion.Model.DAO.GlobalDAO
import com.example.collectorcompanion.Model.Entity.Brand
import com.example.collectorcompanion.Model.Entity.Collection
import com.example.collectorcompanion.Model.Entity.Objet
import com.example.collectorcompanion.Model.Entity.Type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Collection::class, Brand::class, Objet::class, Type::class), version = 1)

abstract class GlobalRoomDatabase : RoomDatabase(){
    abstract fun collectionDao(): GlobalDAO

    private class ApplicationDatabaseCallback(private val scope : CoroutineScope) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch{
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: GlobalRoomDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): GlobalRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GlobalRoomDatabase::class.java,
                    "database"
                ).addCallback(ApplicationDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}