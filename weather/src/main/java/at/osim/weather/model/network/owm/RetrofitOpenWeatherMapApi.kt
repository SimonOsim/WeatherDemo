package at.osim.weather.model.network.owm

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitOpenWeatherMapApi {

    @GET("data/2.5/forecast")
    fun get5DayForecast(@Query("id") cityId: Long)
}