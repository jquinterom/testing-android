package com.cursosandroidant.forecastweather.common.dataAccess

import com.cursosandroidant.forecastweather.entities.WeatherForecastEntity
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class ResponseServerTest {
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    // Validando que se esté leyendo el archivo JSON
    // Uso de backticks
    @Test
    fun `read json file success`() {
        val reader = JSONFileLoader().loadJSONString("weather_forecast_response_success")
        assertThat(reader, `is`(notNullValue()))
        assertThat(reader, containsString("America/Bogota"))
    }

    // Prueba exista
    @Test
    fun `get weather forecast check timezone exist`() {
        val reader = JSONFileLoader().loadJSONString("weather_forecast_response_success") ?: "{}"
        val response = MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(reader)
        mockWebServer.enqueue(response)
        assertThat(response.getBody()?.readUtf8(), containsString("\"timezone\""))
    }

    // Prueba fallida
    @Test
    fun `get weather forecast check file response`() {
        val reader = JSONFileLoader().loadJSONString("weather_forecast_response_fail") ?: "{}"
        val response = MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(reader)
        mockWebServer.enqueue(response)
        assertThat(response.getBody()?.readUtf8(), containsString("message"))
    }

    // Obteniendo Hourly y demas propiedades de la entidad
    @Test
    fun `get weather forecast check contains hourly no empty`(){
        val reader = JSONFileLoader().loadJSONString("weather_forecast_response_success") ?: "{}"
        val response = MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(reader)
        mockWebServer.enqueue(response)
        assertThat(response.getBody()?.readUtf8(), containsString("hourly"))

        val json = Gson().fromJson(response.getBody()?.readUtf8() ?: "{}", WeatherForecastEntity::class.java)
        assertThat(json.hourly.isEmpty(), `is`(false))
    }
}