package com.example.collectorcompanion.Model.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity()

class Objet(
    @ColumnInfo(name = "objet_name") var objetName: String?,
    @ColumnInfo(name = "objet_photo") var objetPhoto: String?,
    @ColumnInfo(name = "objet_year") var objetYear: String?,
    //@ColumnInfo(name = "objet_photo_annexe") var objetPhotoAnnexe: <String>?,
    @ColumnInfo(name = "objet_description") var objetDescription: String?,
    @ForeignKey(entity = Brand::class,parentColumns = arrayOf("idBrand"), childColumns = arrayOf("idObjet"), onDelete = ForeignKey.CASCADE) val idBrand : Int,
    @ForeignKey(entity = Collection::class,parentColumns = arrayOf("idCollection"), childColumns = arrayOf("idObjet"), onDelete = ForeignKey.CASCADE) val idCollection : Int,
    @ForeignKey(entity = Type::class,parentColumns = arrayOf("idType"), childColumns = arrayOf("idObjet"), onDelete = ForeignKey.CASCADE) val idType : Int
) {
    @PrimaryKey(autoGenerate = true) var idObjet: Int = 0
}