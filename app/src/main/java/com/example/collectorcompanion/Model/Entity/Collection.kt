package com.example.collectorcompanion.Model.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()

data class Collection (
        @PrimaryKey val uid: Int,
        @ColumnInfo(name = "collection_name") val collectionName: String?
)