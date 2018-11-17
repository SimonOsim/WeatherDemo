package at.osim.weather.model.network.owm

import at.osim.weather.model.Location
import at.osim.weather.model.Weather
import at.osim.weather.model.network.WeatherApi
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class OpenWeatherMapApiTests {

    private lateinit var server: MockWebServer
    private lateinit var api: WeatherApi

    @Before
    fun setUp() {
        server = MockWebServer().apply { start() }

        api = OpenWeatherMapApiFactory(server.url("").toString(), "test-key")
            .getWeatherApi()
    }

    @Test
    fun getWeatherForecast_REQUEST_OK() {
        val observer = TestObserver<List<Weather>>()

        api.getWeatherForecast(Location.LONDON)
            .subscribe(observer)

        observer.awaitTerminalEvent()
        val result = observer.values()
        observer.assertComplete()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}