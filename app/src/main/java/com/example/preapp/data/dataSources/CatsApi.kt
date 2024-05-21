package com.example.preapp.data.dataSources

import com.example.preapp.data.model.CatJsonModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CatsApi {
    @GET("images/search?limit=10&has_breeds=1")
    suspend fun getCatsInformation(
        @Header("x-api-key") authorization: String =
            "live_g55L0WsNQKDD22I8mhz1A2WSl1saL40U6lPsQWPaxkkSbYvPfhcdb7IzXhOwuNIT"
    ): Response<List<CatJsonModel>>
}