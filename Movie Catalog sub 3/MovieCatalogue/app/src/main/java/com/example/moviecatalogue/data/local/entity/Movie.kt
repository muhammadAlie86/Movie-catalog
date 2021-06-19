package com.example.moviecatalogue.data.local.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_entities")
data class Movie(

    @ColumnInfo(name = "movieId")
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id : Int? ,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdrop_img : String?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title : String?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val img_path : String?,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val language : String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview : String?,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val release_date : String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val vote_average : String?,

    @ColumnInfo(name = "favorite")
    var isFavorite : Boolean = false

    )
