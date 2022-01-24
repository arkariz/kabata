package com.arrkariz.kabata.di

import com.arrkariz.kabata.features.moviesexplore.data.network.ApiService
import com.arrkariz.kabata.features.moviesexplore.data.network.NetworkDataSource
import com.arrkariz.kabata.features.moviesexplore.data.repository.MovieRepository
import com.arrkariz.kabata.features.moviesexplore.domain.repository.IMovieRepository
import com.arrkariz.kabata.features.moviesexplore.domain.usecase.FcmInteractor
import com.arrkariz.kabata.features.moviesexplore.domain.usecase.FcmUseCase
import com.arrkariz.kabata.features.moviesexplore.domain.usecase.MovieInteractor
import com.arrkariz.kabata.features.moviesexplore.domain.usecase.MovieUseCase
import com.arrkariz.kabata.features.moviesexplore.presentation.state.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://moviephrestfullapi.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val networkDataSourceModule = module {
    single { NetworkDataSource(get())}
}

val repositoryModule = module {
    single<IMovieRepository> { MovieRepository(get()) }
}

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<FcmUseCase> { FcmInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}