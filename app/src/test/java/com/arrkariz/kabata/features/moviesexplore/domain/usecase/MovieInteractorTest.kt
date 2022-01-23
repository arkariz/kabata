package com.arrkariz.kabata.features.moviesexplore.domain.usecase

import app.cash.turbine.test
import com.arrkariz.kabata.features.moviesexplore.data.network.response.MovieResponse
import com.arrkariz.kabata.features.moviesexplore.domain.FakeMovieRepository
import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity
import com.arrkariz.kabata.utils.Resources
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class MovieInteractorTest {

    private lateinit var movieInteractor: MovieInteractor
    private lateinit var fakeMovieRepository: FakeMovieRepository
    private lateinit var movie: List<MovieEntity>

    @Before
    fun setUp() {
        fakeMovieRepository = FakeMovieRepository()
        movieInteractor = MovieInteractor(fakeMovieRepository)
        movie = listOf(
            MovieEntity(
            0,
            "test",
            "test",
            "test",
            "test",)
        )
    }

    @Test
    fun `get movies`() = runBlocking {
        val errorTest = HttpException(
            Response.error<List<MovieResponse>>
                (400,
                "{\"key\":[\"somestuff\"]}"
                    .toResponseBody("application/json"
                        .toMediaTypeOrNull()
                    )
            )
        )

        val res = listOf(Resources.Loading<List<MovieEntity>>(message = "loading") ,Resources.Success(movie))
        movieInteractor.getMovieList().test {
            for (i in res){
                when (val emission = awaitItem()) {
                    is Resources.Loading -> {
                        assertThat(emission.message).isEqualTo(i.message)
                        println("Loading Emission: Expected ${emission.message}, found ${i.message}")
                    }
                    is Resources.Success -> {
                        assertThat(emission.data!![0].title).isEqualTo(i.data!![0].title)
                        println("Success Emission: notEmpty \nexpected = ${i.data!![0].title}, found = ${emission.data!![0].title}")
                    }
                    is Resources.Empty -> {
                        assertThat(emission.message).isEqualTo("There is no new movie yet")
                        println("Success Emission: Empty code 204 \nExpected There is no new movie yet, Found ${emission.message}")
                    }
                    is Resources.Error -> {
                        assertThat(emission.message).isEqualTo(errorTest.localizedMessage)
                        println("Error Emission: Expected ${errorTest.localizedMessage}, found ${emission.message} ")
                    }
                }
            }
            cancelAndIgnoreRemainingEvents()
        }
    }

}