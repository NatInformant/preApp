package com.example.preapp.ui.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preapp.data.model.CatInformation
import com.example.preapp.data.model.HttpResponceState
import com.example.preapp.domain.useCases.ImageAndReedUseCase
import kotlinx.coroutines.launch

class MainFragmentViewModel (
    private val imageAndReedUseCase: ImageAndReedUseCase
) :ViewModel() {
    val cats: LiveData<HttpResponceState<List<CatInformation>>> = imageAndReedUseCase.cats

    fun updateCatsList(){
        viewModelScope.launch {
            imageAndReedUseCase.updateCatsList()
        }
    }
}