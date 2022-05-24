package com.odinn.rickandmortyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "person_table")
data class Result(
    @Ignore val created: String = "",
    @Ignore val episode: List<String> = emptyList(),
    @ColumnInfo val gender: String = "",
    @PrimaryKey val id: Int,
    @ColumnInfo val image: String = "",
    @Ignore val location: Location? = null,
    @ColumnInfo val name: String = "",
    @Ignore val origin: Origin? = null,
    @ColumnInfo val species: String = "",
    @ColumnInfo val status: String = "",
    @Ignore val type: String = "",
    @Ignore val url: String= ""
) : Serializable{
    constructor(id: Int, gender: String = "", image: String = "",name: String = "",species: String = "", status: String = ""):
            this(id = id, gender = gender, image = image,name = name, species = species, status = status, created = ""){}
}