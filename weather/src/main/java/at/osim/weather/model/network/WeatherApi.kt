package at.osim.weather.model.network

import at.osim.weather.model.Weather
import io.reactivex.Observable

interface WeatherApi {
    fun getWeatherForecast(): Observable<List<Weather>>
}