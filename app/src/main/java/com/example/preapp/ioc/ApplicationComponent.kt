package com.example.preapp.ioc

import com.example.preapp.data.dataSources.CatsApi
import com.example.preapp.data.dataSources.ImageAndReedRemoteDataSource
import com.example.preapp.domain.useCases.ImageAndReedUseCase
import com.example.preapp.ui.mainFragment.MainFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject
import javax.inject.Scope

@Scope
annotation class AppScope

@Component(modules = [DataModule::class, MainViewModelFactoryModule::class])
@AppScope
interface ApplicationComponent {
    fun injectMainFragment(fragment: MainFragment)
}

@Module
object DataModule {
    @Provides
    @AppScope
    fun getCatsApi(interceptor: AuthorisationInterceptor): CatsApi {
        val baseUrl = "https://api.thecatapi.com/v1/"

        val client = OkHttpClient().newBuilder().apply {
            addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()

    }
}

@Module
object MainViewModelFactoryModule {
    @Provides
    @AppScope
    fun getMainViewModelFactory(
        imageAndReedUseCase: ImageAndReedUseCase
    ) = MainViewModelFactory(imageAndReedUseCase)
}