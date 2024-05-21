package com.example.preapp.data.model

import com.google.gson.annotations.SerializedName

data class CatJsonModel (
    @SerializedName("id") val id:String,
    @SerializedName("breeds") val breeds: List<Breed>,
    @SerializedName("url") val imageUrl:String,
    )