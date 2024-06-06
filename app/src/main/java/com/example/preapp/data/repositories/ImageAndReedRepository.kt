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
    suspend fun updateCatsList(): HttpResponceState<List<CatInformation>> {
        return withContext(Dispatchers.IO) {
            dataSource.updateCatsList()
        }
    }
}