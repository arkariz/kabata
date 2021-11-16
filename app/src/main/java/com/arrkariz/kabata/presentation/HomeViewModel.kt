package com.arrkariz.kabata.presentation

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.arrkariz.kabata.domain.model.MovieListEntity
import com.arrkariz.kabata.domain.model.MovieListState
import com.arrkariz.kabata.domain.model.TokenEntity
import com.arrkariz.kabata.domain.usecase.MovieUseCase
import com.arrkariz.kabata.utils.Resources
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state : State<MovieListState> = _state

    init {
        getMovies()
    }

    private fun getMovies(){
        movieUseCase.getMovieList().onEach {
            when(it) {
                is Resources.Success -> {
                    _state.value = MovieListState(movies = it.data ?: emptyList())
                }
                is Resources.Error -> {
                    _state.value = MovieListState(error = it.message ?: "An unexpected error")
                }
                is Resources.Loading -> {
                    _state.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
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
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }
                task.result?.let {
                    sendToken(it)
                }
            })
        }
    }
}