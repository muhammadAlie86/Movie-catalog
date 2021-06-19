package com.example.moviecatalogue.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tvShow_entities")
data class TvShow (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "tvId")
    @SerializedName("id")
    val id : Int?,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdrop_img : String?,

    @ColumnInfo(name = "original_name")
    @SerializedName("original_name")
    val title : String?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val imgPath : String?,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val language : String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview : String?,

    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    val release_date : String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val vote_average : String?,

    @ColumnInfo(name = "favorite")
    var isFavorite : Boolean = false
)
