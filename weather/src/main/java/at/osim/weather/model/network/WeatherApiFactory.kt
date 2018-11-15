package at.osim.weather.model.network

interface WeatherApiFactory {
    fun getWeatherApi():WeatherApi
}