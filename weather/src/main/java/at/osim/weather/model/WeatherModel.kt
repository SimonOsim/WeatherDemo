package at.osim.weather.model

import at.osim.weather.ListEvent
import at.osim.weather.model.network.WeatherApi
import io.reactivex.Observable

class WeatherModel(private val locationDao: LocationDao, private val api: WeatherApi) : IWeatherModel {

    override fun setLocation(location: Location) {
        locationDao.setCurrentLocation(location)
    }

    override fun location(): Observable<Location> {
        return locationDao.currentLocation()
    }

    override fun weather(day: Int): Observable<Weather> {
        return Observable.never()
    }

    override fun weatherForecast(): Observable<ListEvent<Weather>> {
        return locationDao.currentLocation()
            .flatMap {
                api.getWeatherForecast(it)
            }
            .map { weather ->
                val event = ListEvent(ListEvent.Type.INIT, 0, weather)
                event
            }
    }
}