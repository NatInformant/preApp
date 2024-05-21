package com.example.preapp.data.model

import com.google.gson.annotations.SerializedName

data class CatInformation(
    val id:String,
    val breedName:String,
    val breedDesc: String,
    val imageUrl:String,
    val origin: String,
    val lifeSpan:String,
    val wikiUrl:String,
)
