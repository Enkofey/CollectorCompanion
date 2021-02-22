package com.example.collectorcompanion.Model.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()

data class Collection (
    @ColumnInfo(name = "collection_name") var collectionName: String?,
    @ColumnInfo(name = "collection_image") var collectionImage: String?
){
        @PrimaryKey(autoGenerate = true) var idCollection: Int = 0
}