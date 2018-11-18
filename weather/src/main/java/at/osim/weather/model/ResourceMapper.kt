package at.osim.weather.model

import at.osim.weather.WeatherCondition
import java.util.*

interface ResourceMapper {

    fun conditionIcon(condition: WeatherCondition): Int
    fun condition(condition: WeatherCondition): String
    fun date(date: Date): String
}