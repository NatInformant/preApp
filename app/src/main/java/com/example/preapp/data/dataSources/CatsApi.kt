package com.example.preapp.data.dataSources

import com.example.preapp.data.model.CatDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CatsApi {
    @GET("images/search?limit=10&has_breeds=1")
    suspend fun getCatsInformation(): Response<List<CatDTO>>
}