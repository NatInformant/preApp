package com.example.preapp.data.repositories

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.preapp.data.dataSources.ImageAndReedRemoteDataSource
import com.example.preapp.data.model.CatInformation
import com.example.preapp.data.model.HttpResponceState
import com.example.preapp.ioc.AppScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
class ImageAndReedRepository @Inject constructor(
    private val dataSource: ImageAndReedRemoteDataSource
) {
    private val _cats: MutableLiveData<HttpResponceState<List<CatInformation>>> = MutableLiveData(
        HttpResponceState.Success(
            emptyList()
        )
    )
    val cats:LiveData<HttpResponceState<List<CatInformation>>> = _cats

    suspend fun updateCatsList(){
        val loadedList = withContext(Dispatchers.IO){
            dataSource.updateCatsList()
        }
        _cats.postValue(loadedList)
    }
}