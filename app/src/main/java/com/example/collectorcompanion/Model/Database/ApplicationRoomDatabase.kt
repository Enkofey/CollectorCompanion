package com.example.collectorcompanion.Model.Database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.collectorcompanion.Model.DAO.CollectionDAO
import com.example.collectorcompanion.Model.Entity.Collection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Collection::class), version = 1)

abstract class ApplicationRoomDatabase : RoomDatabase(){
    abstract fun collectionDao(): CollectionDAO

    private class ApplicationDatabaseCallback(private val scope : CoroutineScope) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch{
                    populateDatabase(database.collectionDao())
                }
            }
        }
        suspend fun populateDatabase(collectionDAO: CollectionDAO){
            collectionDAO.deleteAll()

            var collection = Collection(1,"Sony")
            collectionDAO.insert(collection)
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ApplicationRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ApplicationRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationRoomDatabase::class.java,
                    "database"
                ).addCallback(ApplicationDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}