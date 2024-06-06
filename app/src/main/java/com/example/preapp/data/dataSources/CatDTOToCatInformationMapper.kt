package com.example.preapp.data.dataSources

import com.example.preapp.data.model.CatDTO
import com.example.preapp.data.model.CatInformation

class CatDTOToCatInformationMapper {
    companion object {
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