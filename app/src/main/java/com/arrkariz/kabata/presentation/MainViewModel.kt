package com.arrkariz.kabata.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrkariz.kabata.domain.model.TokenEntity
import com.arrkariz.kabata.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun sendToken(token: String){
        val tokenEntity = TokenEntity(token = token)
        viewModelScope.launch {
            movieUseCase.postToken(tokenEntity)
        }
    }
}