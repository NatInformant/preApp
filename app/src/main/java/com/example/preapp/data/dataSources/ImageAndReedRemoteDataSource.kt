package com.example.preapp.data.dataSources

import com.example.preapp.data.model.CatInformation
import com.example.preapp.data.model.HttpResponceState
import com.example.preapp.ioc.AppScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject


@AppScope
class ImageAndReedRemoteDataSource @Inject constructor(
    private val catsApi: CatsApi
)  {

    val cats: MutableList<CatInformation> = ArrayList()

    suspend fun updateCatsList(): HttpResponceState<List<CatInformation>> {
        cats.clear()
        kotlin.runCatching {
            catsApi.getCatsInformation()
        }.fold(
            onSuccess = { responce ->
                if (responce.isSuccessful) {
                    responce.body()?.forEach {
                        cats.add(
                            CatInformation(
                                id = it.id,
                                breedName= it.breeds[0].name,
                                breedDesc =  it.breeds[0].description,
                                imageUrl = it.imageUrl,
                                origin = it.breeds[0].origin,
                                lifeSpan = it.breeds[0].lifeSpan,
                                wikiUrl = it.breeds[0].wikiUrl
                            )
                        )
                    }

                    return HttpResponceState.Success(cats.toList())
                } else {
                    return HttpResponceState.Failure(responce.message())
                }
            },
            onFailure = { throwable ->
                return HttpResponceState.Failure(throwable.message ?: "failure")
            }
        )
    }
}