package at.osim.weather.mvvm

import io.reactivex.Observable

interface WeatherViewModel {

    fun condition(): Observable<String>

    fun weatherIcon(): Observable<String>

    fun minTemperature(): Observable<Float>
    fun maxTemperature(): Observable<Float>

    fun date(): Observable<String>

}