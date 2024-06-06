package com.example.preapp.data.dataSources

import com.example.preapp.data.model.CatInformation
import com.example.preapp.data.model.HttpResponceState
import com.example.preapp.ioc.AppScope
import javax.inject.Inject


@AppScope
class ImageAndReedRemoteDataSource @Inject constructor(
    private val catsApi: CatsApi
)  {

    suspend fun updateCatsList(): HttpResponceState<List<CatInformation>> {
        val cats: MutableList<CatInformation> = ArrayList()

        kotlin.runCatching {
            catsApi.getCatsInformation()
        }.fold(
            onSuccess = { responce ->
                if (responce.isSuccessful) {
                    responce.body()?.forEach {
                        cats.add(
                            CatDTOToCatInformationMapper.catInformationFromCatDTO(it)
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