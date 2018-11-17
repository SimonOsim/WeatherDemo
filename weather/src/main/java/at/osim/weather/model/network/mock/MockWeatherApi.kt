package at.osim.weather.model.network.mock

import at.osim.weather.Intensity
import at.osim.weather.WeatherCondition
import at.osim.weather.model.Location
import at.osim.weather.model.Weather
import at.osim.weather.model.network.WeatherApi
import io.reactivex.Observable
import java.util.*

class MockWeatherApi : WeatherApi {
    override fun getWeatherForecast(location: Location, units: Weather.Units): Observable<List<Weather>> {
        return Observable.just(
            listOf(Weather().apply {
                date = Date()
                temp = 10.0f
                tempMin = 9.0f
                tempMax = 13.0f
                condition = WeatherCondition.CLOUDS
                intensity = Intensity.MIDDLE
                pressure = 12.0f
                humidity = 60
                windDeg = 0
                windSpeed = 6.0f
            },
                Weather().apply {
                    date = Date()
                    temp = 11.0f
                    tempMin = 9.0f
                    tempMax = 13.0f
                    condition = WeatherCondition.CLEAR
                    intensity = Intensity.MIDDLE
                    pressure = 12.0f
                    humidity = 60
                    windDeg = 0
                    windSpeed = 6.0f
                },
                Weather().apply {
                    date = Date()
                    temp = 12.0f
                    tempMin = 8.0f
                    tempMax = 14.0f
                    condition = WeatherCondition.CLOUDS
                    intensity = Intensity.LIGHT
                    pressure = 12.0f
                    humidity = 60
                    windDeg = 0
                    windSpeed = 6.0f
                })
        )
    }
}