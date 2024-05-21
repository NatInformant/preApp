package com.example.preapp.data.model

import android.health.connect.datatypes.DataOrigin
import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("name") val name:String,
    @SerializedName("description") val description:String,
    @SerializedName("origin") val origin: String,
    @SerializedName("life_span") val lifeSpan:String,
    @SerializedName("wikipedia_url") val wikiUrl:String,
)
