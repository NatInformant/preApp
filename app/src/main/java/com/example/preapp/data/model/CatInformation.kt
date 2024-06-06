package com.example.preapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class CatInformation(
    val id: String,
    val breedName: String,
    val breedDesc: String,
    val imageUrl: String,
    val origin: String,
    val lifeSpan: String,
    val wikiUrl: String,
) : Parcelable
