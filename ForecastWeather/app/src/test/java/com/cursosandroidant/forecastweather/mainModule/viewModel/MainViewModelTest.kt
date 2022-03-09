package com.cursosandroidant.forecastweather.mainModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cursosandroidant.forecastweather.MainCoroutineRule
import com.cursosandroidant.forecastweather.common.dataAccess.WeatherForecastService
import com.cursosandroidant.forecastweather.entities.WeatherForecastEntity
import com.cursosandroidant.historicalweatherref.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var service: WeatherForecastService

    companion object {
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Before
    fun setup() {
        mainViewModel = MainViewModel()
        service = retrofit.create(WeatherForecastService::class.java)
    }

    // Validando el resultado con corrutinas
    @Test
    fun checkCurrentWeatherIsNotNullTest() {
        runBlocking {
            val result = service.getWeatherForecastByCoordinates(
                1.85431, -76.0516,
                "6364546cb00c113bff0065ac8aea2438", "metric", "en"
            )

            assertThat(result.current, `is`(notNullValue()))
        }
    }

    // Validando la zona horaria
    @Test
    fun checkTimezoneReturnsBogotaTest() {
        runBlocking {
            val result = service.getWeatherForecastByCoordinates(
                1.85431, -76.0516,
                "6364546cb00c113bff0065ac8aea2438", "metric", "en"
            )

            assertThat(result.timezone, `is`("America/Bogota"))
        }
    }

    @Test
    fun checkErrorResponseWithOnlyCoordinatesTest() {
        runBlocking {
            try {
                service.getWeatherForecastByCoordinates(
                    1.85431, -76.0516,
                    "", "", ""
                )
            } catch (e: Exception) {
                assertThat(e.localizedMessage, `is`("HTTP 401 Unauthorized"))
            }
        }
    }

    @Test
    fun checkHourlySizeTest() {
        runBlocking {
            mainViewModel.getWeatherAndForecast(
                1.85431, -76.0516,
                "6364546cb00c113bff0065ac8aea2438", "metric", "en"
            )
            val result = mainViewModel.getResult().getOrAwaitValue ()
            assertThat(result.hourly.size, `is`(48))
        }
    }
}