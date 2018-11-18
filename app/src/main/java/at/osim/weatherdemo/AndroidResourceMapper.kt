package at.osim.weatherdemo

import android.content.Context
import android.content.res.Resources
import at.osim.weather.WeatherCondition
import at.osim.weather.model.ResourceMapper
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AndroidResourceMapper(private val context: Context, private val theme: Resources.Theme) : ResourceMapper {

    private val dateFormat: DateFormat = SimpleDateFormat.getDateInstance()

    
    override fun conditionIcon(condition: WeatherCondition): Int {
        return when (condition) {
            WeatherCondition.THUNDERSTORM -> R.drawable.ic_wi_thunderstorm
            WeatherCondition.DRIZZLE -> R.drawable.ic_wi_sprinkle
            WeatherCondition.RAIN -> R.drawable.ic_wi_showers
            WeatherCondition.SNOW -> R.drawable.ic_wi_snow
            WeatherCondition.CLEAR -> R.drawable.ic_wi_day_sunny
            WeatherCondition.FOG -> R.drawable.ic_wi_fog
            WeatherCondition.CLOUDS -> R.drawable.ic_wi_cloudy
            WeatherCondition.UNKNOWN -> R.drawable.ic_wi_day_sunny
        }
    }

    override fun condition(condition: WeatherCondition): String {
        return when (condition) {
            WeatherCondition.THUNDERSTORM -> context.getString(R.string.thunderstorm)
            WeatherCondition.DRIZZLE -> context.getString(R.string.drizzle)
            WeatherCondition.RAIN -> context.getString(R.string.rain)
            WeatherCondition.SNOW -> context.getString(R.string.snow)
            WeatherCondition.CLEAR -> context.getString(R.string.clear)
            WeatherCondition.FOG -> context.getString(R.string.fog)
            WeatherCondition.CLOUDS -> context.getString(R.string.clouds)
            WeatherCondition.UNKNOWN -> context.getString(R.string.not_available)
        }
    }

    override fun date(date: Date): String {
        return dateFormat.format(date)
    }
}