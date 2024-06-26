package com.example.preapp.data.dataSources

import com.example.preapp.data.dataSources.ImageAndReedRemoteDataSource.MapperObject.catInformationFromCatDTO
import com.example.preapp.data.model.CatDTO
import com.example.preapp.data.model.CatInformation
import com.example.preapp.data.model.HttpResponceState
import com.example.preapp.ioc.AppScope
import javax.inject.Inject


@AppScope
class ImageAndReedRemoteDataSource @Inject constructor(
    private val catsApi: CatsApi
) {

    suspend fun updateCatsList(): HttpResponceState<List<CatInformation>> {

        kotlin.runCatching {
            catsApi.getCatsInformation()
        }.fold(
            onSuccess = { responce ->
                if (responce.isSuccessful) {
                    val result =
                        responce.body()?.map(::catInformationFromCatDTO)?.toList() ?: emptyList()
                    return HttpResponceState.Success(result)
                } else {
                    return HttpResponceState.Failure(responce.message())
                }
            },
            onFailure = { throwable ->
                return HttpResponceState.Failure(throwable.message ?: "failure")
            }
        )
    }

    object MapperObject {
        fun catInformationFromCatDTO(catDTO: CatDTO) =
            CatInformation(
                id = catDTO.id,
                breedName = catDTO.breeds[0].name,
                breedDesc = catDTO.breeds[0].description,
                imageUrl = catDTO.imageUrl,
                origin = catDTO.breeds[0].origin,
                lifeSpan = catDTO.breeds[0].lifeSpan,
                wikiUrl = catDTO.breeds[0].wikiUrl
            )
    }
}
