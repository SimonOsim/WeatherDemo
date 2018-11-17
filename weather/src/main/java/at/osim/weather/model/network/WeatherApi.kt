package at.osim.weather.model.network

import at.osim.weather.model.Location
import at.osim.weather.model.Weather
import io.reactivex.Observable

interface WeatherApi {
    fun getWeatherForecast(location: Location, units: Weather.Units=Weather.Units.CELSIUS): Observable<List<Weather>>
}