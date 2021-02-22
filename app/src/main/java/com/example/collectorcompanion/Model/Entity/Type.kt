package com.example.collectorcompanion.Model.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity()

class Type (
    @ColumnInfo(name = "type_name") val typeName: String?,
    @ForeignKey(entity = Brand::class,parentColumns = arrayOf("idBrand"), childColumns = arrayOf("idType")) val idBrand : Int
) {
    @PrimaryKey(autoGenerate = true) var idType: Int = 0
}