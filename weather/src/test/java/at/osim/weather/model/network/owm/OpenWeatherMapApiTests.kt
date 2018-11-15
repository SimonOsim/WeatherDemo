package at.osim.weather.model.network.owm

import at.osim.weather.model.network.WeatherApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class OpenWeatherMapApiTests {

    private lateinit var server: MockWebServer
    private lateinit var api: WeatherApi

    @Before
    fun setUp() {
        server = MockWebServer().apply { start() }

        api = OpenWeatherMapApiFactory(server.url("").toString(), "test-key").getWeatherApi()
    }

    @Test
    fun getWeatherForecast_REQUEST_OK(){
        throw NotImplementedError()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}