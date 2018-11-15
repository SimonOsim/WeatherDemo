package at.osim.weather.model.network.owm

import at.osim.weather.model.network.WeatherApi
import at.osim.weather.model.network.WeatherApiFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class OpenWeatherMapApiFactory(baseUrl: String, private val apiKey: String) : WeatherApiFactory {

    companion object {
        private const val API_KEY_NAME = "APPID"
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val url = request.url().newBuilder().addQueryParameter(API_KEY_NAME, apiKey).build()
                chain.proceed(request.newBuilder().url(url).build())
            }
            .build())
        .build()

    override fun getWeatherApi(): WeatherApi {
        return OpenWeatherMapApi(retrofit.create(RetrofitOpenWeatherMapApi::class.java))
    }
}