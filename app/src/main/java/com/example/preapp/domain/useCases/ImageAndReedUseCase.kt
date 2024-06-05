package com.example.preapp.domain.useCases

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.example.preapp.data.model.CatInformation
import com.example.preapp.data.model.HttpResponceState
import com.example.preapp.data.repositories.ImageAndReedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageAndReedUseCase @Inject constructor(
    private val repository: ImageAndReedRepository
)  {
    val cats: LiveData<HttpResponceState<List<CatInformation>>> = repository.cats

    suspend fun updateCatsList(){
        withContext(Dispatchers.IO){
            repository.updateCatsList()
        }
    }
}