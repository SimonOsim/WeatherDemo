package at.osim.weather.model

import at.osim.weather.Intensity
import at.osim.weather.WeatherCondition
import java.util.*

class Weather {
    var temp = 0.0f

    var condition = WeatherCondition.CLEAR
    var intensity = Intensity.MIDDLE

    var pressure = 0.0f
    var humidity = 0
    var tempMin = 0.0f
    var tempMax = 0.0f

    var windSpeed = 0.0f
    var windDeg = 0

    var date = Date()

    enum class Units {
        KELVIN,
        FAHRENHEIT,
        CELSIUS
    }
}