package at.osim.weather.model.network.owm

import at.osim.weather.WeatherCondition
import at.osim.weather.model.Location
import at.osim.weather.model.Weather
import at.osim.weather.model.network.WeatherApi
import io.reactivex.Observable
import java.io.IOException
import java.util.*

class OpenWeatherMapApi(private val api: RetrofitOpenWeatherMapApi) : WeatherApi {

    override fun getWeatherForecast(location: Location, units: Weather.Units): Observable<List<Weather>> {
        val u = when (units) {
            Weather.Units.CELSIUS -> "metric"
            Weather.Units.FAHRENHEIT -> "imperial"
            else -> ""
        }
        return api.get5DayForecast(location.id, u)
            .map { response ->
                if (response.isSuccessful) {
                    response.body()
                } else {
                    throw IOException()
                }
            }
            .map { weatherResponse ->
                val result = ArrayList<Weather>(weatherResponse.list?.size ?: 0)
                for (weather in weatherResponse.list ?: emptyList()) {
                    result.add(Weather().apply {
                        temp = weather?.main?.temp ?: 0.0f
                        tempMin = weather?.main?.temp_min ?: 0.0f
                        tempMax = weather?.main?.temp_max ?: 0.0f
                        condition = when (weather?.weather?.firstOrNull()?.id) {
                            in 200..299 -> WeatherCondition.THUNDERSTORM
                            in 300..399 -> WeatherCondition.DRIZZLE
                            in 500..599 -> WeatherCondition.RAIN
                            in 600..699 -> WeatherCondition.SNOW
                            in 700..799 -> WeatherCondition.FOG
                            800 -> WeatherCondition.CLEAR
                            in 801..899 -> WeatherCondition.CLOUDS
                            else -> WeatherCondition.UNKNOWN
                        }
                        humidity = weather?.main?.humidity ?: -1
                        windSpeed = weather?.wind?.speed ?: 0.0f
                        windDeg = weather?.wind?.deg?.toInt() ?: 0
                        pressure = weather?.main?.pressure ?: -1.0f
                        date = Date(weather?.dt ?: Date().time)
                    })
                }
                result.sortBy { weather ->
                    weather.date
                }
                result
            }
    }
}