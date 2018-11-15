package at.osim.weather.model

import at.osim.weather.ListEvent
import io.reactivex.Observable

/**
 * The model for our weather App.
 */
interface IWeatherModel {
    /**
     * Set the given location as default.
     * [weather] and [weatherForecast] will return the weather for this location.
     */
    fun setLocation(location: Location)

    /**
     * Get the currently selected location
     */
    fun location(): Observable<Location>

    /**
     * Get updates for the weather
     * @param day Day to get the weather for. 0 is the current day
     */
    fun weather(day: Int): Observable<Weather>

    /**
     * Get a list of days with the weather for the current location
     */
    fun weatherForecast(): Observable<ListEvent<Weather>>
}