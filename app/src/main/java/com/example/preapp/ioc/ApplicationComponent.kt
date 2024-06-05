package com.example.preapp.ioc

import com.example.preapp.data.dataSources.ImageAndReedRemoteDataSource
import com.example.preapp.domain.useCases.ImageAndReedUseCase
import com.example.preapp.ui.mainFragment.MainFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Scope

@Scope
annotation class AppScope

@Component(modules = [DataModule::class, MainViewModelFactory::class])
@AppScope
interface ApplicationComponent{
    fun injectMainFragment(fragment: MainFragment)
}

@Module
object DataModule{
    @Provides
    @AppScope
    fun getImageAndReedRemoteDataSource() = ImageAndReedRemoteDataSource()
}

@Module
object MainViewModelFactoryModule{
    @Provides
    @AppScope
    fun getMainViewModelFactory(
        imageAndReedUseCase:ImageAndReedUseCase
    ) = MainViewModelFactory(imageAndReedUseCase)
}