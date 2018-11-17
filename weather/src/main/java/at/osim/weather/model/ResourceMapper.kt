package at.osim.weather.model

import at.osim.weather.WeatherCondition
import java.util.*

interface ResourceMapper {

    fun conditionIcon(condition: WeatherCondition): String
    fun condition(condition: WeatherCondition): String
    fun date(date: Date): String
}