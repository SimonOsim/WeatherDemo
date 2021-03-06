package at.osim.weather.model

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class LocationDao {

    private val currentLocation = BehaviorSubject.create<Location>()

    fun allLocations(): Observable<Location> {
        return Observable.just(Location.LONDON)
    }

    fun currentLocation(): Observable<Location> {
        return currentLocation.hide()
    }

    fun setCurrentLocation(location: Location) {
        currentLocation.onNext(location)
    }
}