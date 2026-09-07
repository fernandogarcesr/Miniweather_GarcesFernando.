package utilices

import com.fernandog.miniweather_garcesf.R
import android.content.Context
import domain.Weather

class WeatherService(private val context: Context) {

    private val weatherIcons = arrayOf(
        R.drawable.ic_snowy,
        R.drawable.ic_windy,
        R.drawable.ic_stormy,
        R.drawable.ic_rainy,
        R.drawable.ic_cloudy,
        R.drawable.ic_sunny
    )
  val weatherStates = arrayOf(
      context.getString(R.string.snowy),
      context.getString(R.string.windy),
      context.getString(R.string.stormy),
      context.getString(R.string.rainy),
      context.getString(R.string.cloudy),
      context.getString(R.string.sunny))

    fun getCities():Array<String>{
        return arrayOf(
            "Ciudad Obregon",
            "Hermosillo",
            "Ciudad de Mexico",
            "Monterrey",
            "Mazatlan")

    }

    fun generateWeather(): Weather {

        val temp = (-15..50).random()
        var weatherIndex = -1
        when(temp){
            in -15..0 -> weatherIndex = 0
            in 1..18 -> weatherIndex = (1..4).random()
            in 19..25 -> weatherIndex = (4..5).random()
            else -> weatherIndex = 5

        }

        return Weather(temp, weatherStates[weatherIndex])
    }

    fun getWeatherIcon(weatherDescription: String): Int {
        val index = weatherStates.indexOf(weatherDescription)
        return if (index != -1) weatherIcons[index] else R.drawable.ic_sunny
    }
}