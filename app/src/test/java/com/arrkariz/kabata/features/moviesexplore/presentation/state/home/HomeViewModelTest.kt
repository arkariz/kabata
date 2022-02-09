package com.arrkariz.kabata.features.moviesexplore.presentation.state.home

import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity
import com.arrkariz.kabata.features.moviesexplore.domain.model.TokenEntity
import com.arrkariz.kabata.features.moviesexplore.domain.usecase.FakeFcmUseCase
import com.arrkariz.kabata.features.moviesexplore.domain.usecase.FakeMovieUseCase
import com.arrkariz.kabata.utils.GetMovieCase
import com.arrkariz.kabata.utils.GetTokenCase
import com.arrkariz.kabata.utils.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private lateinit var movie: MovieEntity
    private lateinit var token: TokenEntity

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        movie = MovieEntity(
                0,
                "test",
                "test",
                "test",
                "test",
        "test",
        "test",
        "test",
        "test",
        "test")

        token = TokenEntity("test token")
    }

    @Test
    fun `get movies flow success`() {
        val fakeFcmUseCase = FakeFcmUseCase(GetMovieCase.SUCCESS, null)
        val fakeMovieUseCase = FakeMovieUseCase(GetMovieCase.SUCCESS, movie)
        val homeViewModel = HomeViewModel(fakeMovieUseCase ,fakeFcmUseCase)

        val result = homeViewModel.state.value.movies
        assertThat(result.last().title).isEqualTo(movie.title)
        println("Get Movies: Expected Title is ${movie.title}, found Title is ${result.last().title}")

    }

    @Test
    fun `get movies flow success empty`() {
        val fakeFcmUseCase = FakeFcmUseCase(GetMovieCase.SUCCESS, null)
        val fakeMovieUseCase = FakeMovieUseCase(GetMovieCase.EMPTY, null)
        val homeViewModel = HomeViewModel(fakeMovieUseCase ,fakeFcmUseCase)

        val result = homeViewModel.state.value.empty
        assertThat(result).isEqualTo(GetMovieCase.EMPTY)
        println("Get Movies: Expected ${GetMovieCase.EMPTY}, $result")

    }

    @Test
    fun `get movies flow error`() {
        val fakeFcmUseCase = FakeFcmUseCase(GetMovieCase.SUCCESS, null)
        val fakeMovieUseCase = FakeMovieUseCase(GetMovieCase.ERROR, null)
        val homeViewModel = HomeViewModel(fakeMovieUseCase ,fakeFcmUseCase)

        val result = homeViewModel.state.value.error
        assertThat(result).isEqualTo(GetMovieCase.ERROR)
        println("Get Movies: Expected ${GetMovieCase.ERROR}, found $result")

    }

    @Test
    fun `get new movie flow success`() {
        val fakeFcmUseCase = FakeFcmUseCase(GetMovieCase.SUCCESS, null)
        val fakeMovieUseCase = FakeMovieUseCase(GetMovieCase.SUCCESS, movie)
        val homeViewModel = HomeViewModel(fakeMovieUseCase ,fakeFcmUseCase)

        val result = homeViewModel.newMovieState.value
        assertThat(result.movie.title).isEqualTo(movie.title)
        println("Get Movies: Expected Title is ${movie.title}, found Title is ${result.movie.title}")

    }

    @Test
    fun `get new movie flow success empty`() {
        val fakeFcmUseCase = FakeFcmUseCase(GetMovieCase.SUCCESS, null)
        val fakeMovieUseCase = FakeMovieUseCase(GetMovieCase.EMPTY, null)
        val homeViewModel = HomeViewModel(fakeMovieUseCase ,fakeFcmUseCase)

        val result = homeViewModel.newMovieState.value
        assertThat(result.empty).isEqualTo(GetMovieCase.EMPTY)
        println("Get Movies: Expected ${GetMovieCase.EMPTY}, found ${result.empty}")

    }

    @Test
    fun `get new movie flow error`() {
        val fakeFcmUseCase = FakeFcmUseCase(GetMovieCase.SUCCESS, null)
        val fakeMovieUseCase = FakeMovieUseCase(GetMovieCase.ERROR, null)
        val homeViewModel = HomeViewModel(fakeMovieUseCase ,fakeFcmUseCase)

        val result = homeViewModel.newMovieState.value
        assertThat(result.error).isEqualTo(GetMovieCase.ERROR)
        println("Get Movies: Expected ${GetMovieCase.ERROR}, found ${result.error}")

    }

    @Test
    fun `get token success`() = runBlocking{
        val fakeFcmUseCase = FakeFcmUseCase(GetTokenCase.SUCCESS, token)
        val fakeMovieUseCase = FakeMovieUseCase(GetMovieCase.SUCCESS, null)
        val homeViewModel = HomeViewModel(fakeMovieUseCase ,fakeFcmUseCase)

        val result = homeViewModel.getToken()
        assertThat(result.last().token).isEqualTo(token.token)
        println("Get Movies: Expected ${token.token}, found ${result.last().token}")

    }

    @Test
    fun `get token empty`() = runBlocking{
        val fakeFcmUseCase = FakeFcmUseCase(GetTokenCase.EMPTY, null)
        val fakeMovieUseCase = FakeMovieUseCase(GetMovieCase.EMPTY, null)
        val homeViewModel = HomeViewModel(fakeMovieUseCase ,fakeFcmUseCase)

        val result = homeViewModel.getToken()
        assertThat(result).isEqualTo(emptyList<TokenEntity>())
        println("Get Movies: Expected ${emptyList<TokenEntity>()}, found $result")

    }

    @Test
    fun `get token error`() = runBlocking{
        val fakeFcmUseCase = FakeFcmUseCase(GetTokenCase.ERROR, null)
        val fakeMovieUseCase = FakeMovieUseCase(GetMovieCase.ERROR, null)
        val homeViewModel = HomeViewModel(fakeMovieUseCase ,fakeFcmUseCase)

        val result = homeViewModel.getToken()
        assertThat(result).isEqualTo(emptyList<TokenEntity>())
        println("Get Movies: Expected ${emptyList<TokenEntity>()}, found $result")

    }
}