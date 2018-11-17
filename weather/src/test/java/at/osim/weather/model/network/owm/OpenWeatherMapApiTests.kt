package at.osim.weather.model.network.owm

import at.osim.weather.WeatherCondition
import at.osim.weather.model.Location
import at.osim.weather.model.Weather
import at.osim.weather.model.network.WeatherApi
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit

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

        val request = server.takeRequest(1000, TimeUnit.MILLISECONDS).requestLine
        assertEquals("GET /data/2.5/forecast?id=${Location.LONDON.id}&units=metric&APPID=test-key HTTP/1.1", request)
    }

    @Test
    fun getWeatherForecast_RESULT_OK() {
        server.enqueue(
            MockResponse().setResponseCode(200).setBody(
                "{\"cod\":\"200\",\"message\":0.0036,\"cnt\":40,\"list\":[{\"dt\":1542441600000,\"main\":{\"temp\":261.45,\"temp_min\":259.086,\"temp_max\":261.45,\"pressure\":1023.48,\"sea_level\":1045.39,\"grnd_level\":1023.48,\"humidity\":79,\"temp_kf\":2.37},\n" +
                        "\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"Some clouds\",\"icon\":\"02n\"}],\"clouds\":{\"all\":8},\n" +
                        "\"wind\":{\"speed\":4.77,\"deg\":232.505},\n" +
                        "\"snow\":{},\n" +
                        "\"sys\":{\"pod\":\"n\"},\n" +
                        "\"dt_txt\":\"2017-01-30 18:00:00\"}],\"city\":{\"id\":524901,\"name\":\"Moscow\",\"coord\":{\"lat\":55.7522,\"lon\":37.6156},\n" +
                        "\"country\":\"none\"}}"
            )
        )

        val observer = TestObserver<List<Weather>>()

        api.getWeatherForecast(Location.LONDON)
            .subscribe(observer)

        observer.awaitTerminalEvent(1000, TimeUnit.MILLISECONDS)
        val weather = observer.values().first().first()

        assertEquals(WeatherCondition.CLOUDS, weather.condition)
        assertEquals(261.45f, weather.temp)
        assertEquals(259.086f, weather.tempMin)
        assertEquals(261.45f, weather.tempMax)
        assertEquals(79, weather.humidity)
        assertEquals(1023.48f, weather.pressure)

        val date = GregorianCalendar.getInstance().apply { time = weather.date }
        assertEquals(2018, date.get(Calendar.YEAR))
        assertEquals(10, date.get(Calendar.MONTH))
        assertEquals(17, date.get(Calendar.DAY_OF_MONTH))

    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}