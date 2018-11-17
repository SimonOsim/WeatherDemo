package at.osim.weather.model.network.owm

data class WeatherResponse(
    var city: City?,
    var cnt: Int?,
    var cod: String?,
    var list: List<WeatherDetails?>?,
    var message: Float?
) {
    data class WeatherDetails(
        var clouds: Clouds?,
        var dt: Long?,
        var dt_txt: String?,
        var main: Main?,
        var sys: Sys?,
        var weather: List<Weather?>?,
        var wind: Wind?
    ) {
        data class Sys(
            var pod: String?
        )

        data class Weather(
            var description: String?,
            var icon: String?,
            var id: Int?,
            var main: String?
        )

        data class Clouds(
            var all: Int?
        )

        data class Main(
            var grnd_level: Float?,
            var humidity: Int?,
            var pressure: Float?,
            var sea_level: Float?,
            var temp: Float?,
            var temp_kf: Float?,
            var temp_max: Float?,
            var temp_min: Float?
        )

        data class Wind(
            var deg: Float?,
            var speed: Float?
        )
    }

    data class City(
        var coord: Coord?,
        var country: String?,
        var id: Long?,
        var name: String?
    ) {
        data class Coord(
            var lat: Float?,
            var lon: Float?
        )
    }
}