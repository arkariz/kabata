package com.arrkariz.kabata.presentation

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrkariz.kabata.domain.model.MovieEntity
import com.arrkariz.kabata.domain.model.MovieListState
import com.arrkariz.kabata.domain.model.MovieState
import com.arrkariz.kabata.domain.model.TokenEntity
import com.arrkariz.kabata.domain.usecase.MovieUseCase
import com.arrkariz.kabata.utils.Resources
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class HomeViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state : State<MovieListState> = _state

    private val _newMovieState = mutableStateOf(MovieState())
    val newMovieState: State<MovieState> = _newMovieState

    init {
        fetchFirebaseToken()
        getNewMovie()
        getMovies()
    }

    private fun getMovies(){
        movieUseCase.getMovieList().onEach {
            when(it) {
                is Resources.Success -> {
                    _state.value = MovieListState(movies = it.data ?: emptyList(), isLoading = false)
                }
                is Resources.Error -> {
                    _state.value = MovieListState(error = it.message ?: "An unexpected error", isLoading = false)
                }
                is Resources.Loading -> {
                    _state.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getNewMovie(){
        movieUseCase.getNewestMovie().onEach {
            when(it) {
                is Resources.Success -> {
                    _newMovieState.value = MovieState(movie = it.data ?: MovieEntity(0, "", "", "", "0.0"), isLoading = false)
                }
                is Resources.Error -> {
                    _newMovieState.value = MovieState(error = it.message ?: "An unexpected error", isLoading = false)
                }
                is Resources.Loading -> {
                    _newMovieState.value = MovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    private suspend fun getToken(): List<TokenEntity>{
       return movieUseCase.getToken()
    }

    private fun sendToken(token: String){
        val tokenEntity = TokenEntity(token = token)
        viewModelScope.launch {
            movieUseCase.postToken(tokenEntity)
        }
    }

    private fun fetchFirebaseToken() {
        viewModelScope.launch {
            delay(200L)
            val tokens = getToken()
            if (tokens.isNullOrEmpty()){
                Log.d("SendToken", "Empty Token")
            } else {
                FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                        return@OnCompleteListener
                    }
                    task.result?.let {
                        for (token in tokens){
                            if (token.token != it){
                                Log.d("SendToken", "Token successfully Post")
                                sendToken(it)
                            } else{
                                Log.d("SendToken", "Token Already Posted")
                            }
                        }
                    }
                })
            }
        }
    }
}