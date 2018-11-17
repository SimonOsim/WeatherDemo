package at.osim.weather.model.network.mock

import at.osim.weather.model.network.WeatherApi
import at.osim.weather.model.network.WeatherApiFactory

class MockWeatherApiFactory : WeatherApiFactory {
    override fun getWeatherApi(): WeatherApi {
        return MockWeatherApi()
    }
}