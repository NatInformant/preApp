package com.example.preapp.ui.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preapp.data.model.CatInformation
import com.example.preapp.data.model.HttpResponceState
import com.example.preapp.domain.useCases.ImageAndReedUseCase
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val imageAndReedUseCase: ImageAndReedUseCase
) : ViewModel() {
    val cats: MutableLiveData<HttpResponceState<List<CatInformation>>> =
        MutableLiveData(HttpResponceState.Loading)

    fun updateCatsList() {
        viewModelScope.launch {
            cats.postValue(imageAndReedUseCase.updateCatsList())
        }
    }
}