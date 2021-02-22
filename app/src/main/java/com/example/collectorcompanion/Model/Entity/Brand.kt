package com.example.collectorcompanion.Model.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity()

class Brand (
    @ColumnInfo(name = "brand_name") val brandName: String?,
    @ColumnInfo(name = "brand_image") val brandImage: String?,
    @ForeignKey(entity = Type::class,parentColumns = arrayOf("idType"), childColumns = arrayOf("idBrand")) val idType : Int
) {
    @PrimaryKey(autoGenerate = true) var idBrand: Int = 0
}