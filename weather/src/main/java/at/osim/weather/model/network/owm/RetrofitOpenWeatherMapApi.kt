package at.osim.weather.model.network.owm

import at.osim.weather.model.Weather
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitOpenWeatherMapApi {

    @GET("data/2.5/forecast")
    fun get5DayForecast(@Query("id") cityId: Long, @Query("units") units:String = "metric"): Observable<Response<WeatherResponse>>
}