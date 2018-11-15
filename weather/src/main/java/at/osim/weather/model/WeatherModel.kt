package at.osim.weather.model

import at.osim.weather.ListEvent
import io.reactivex.Observable

class WeatherModel(private val locationDao: LocationDao) : IWeatherModel {

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
        return Observable.never()
    }
}