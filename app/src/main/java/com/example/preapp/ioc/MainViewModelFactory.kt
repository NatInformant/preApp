package com.example.preapp.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.preapp.domain.useCases.ImageAndReedUseCase
import com.example.preapp.ui.mainFragment.MainFragmentViewModel
import dagger.Module
import javax.inject.Inject

@Module
@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @Inject constructor(
    val imageAndReedUseCase: ImageAndReedUseCase
) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainFragmentViewModel(
            imageAndReedUseCase
        ) as T
    }
}