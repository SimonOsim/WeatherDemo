package at.osim.weather.mvvm

import at.osim.weather.ListEvent
import at.osim.weather.model.IWeatherModel
import at.osim.weather.model.Location
import at.osim.weather.model.ResourceMapper
import io.reactivex.Observable
import java.util.*

class WeatherListViewModel(private val model: IWeatherModel, private val resources: ResourceMapper) {

    fun weather(): Observable<ListEvent<WeatherViewData>> {
        return model.weatherForecast().map { event ->
            val items = event.items.map { weather ->
                WeatherViewData(
                    resources.condition(weather.condition),
                    resources.conditionIcon(weather.condition),
                    weather.tempMin,
                    weather.tempMax,
                    resources.date(weather.date)
                )
            }

            ListEvent(event.type, event.pos, items)
        }
    }

    fun weatherDetails(): Observable<WeatherViewData> {
        return Observable.never()
    }

    fun selectDay(date: Date) {

    }

    fun selectCity(location: Location) {
        model.setLocation(location)
    }

}