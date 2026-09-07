package com.fernandog.miniweather_garcesf

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.ImageView
import androidx.core.view.WindowCompat
import utilices.WeatherService
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    /**
     * para que al minimizar la app y volverla a abrir se actualice el mensaje se tiene que poner
     * ese codigo dentro de la funcion onResume que es la que se ejecuta al minimizar y abrir.
     * el tvGreeting se tuvo que cambiar a propiedad de la clase para que setGreeting pueda usarlo
     * desde fuera en el onCreate
     */
    private lateinit var tvGreeting: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false

        setContentView(R.layout.activity_main)
        tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        val tvCity = findViewById<TextView>(R.id.tvCity)
        val ivWeather = findViewById<ImageView>(R.id.ivWeather)
        val tvTemperature = findViewById<TextView>(R.id.tvTemperature)
        val tvWeather = findViewById<TextView>(R.id.tvWeather)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val city = intent.getStringExtra("city") ?: "Ciudad Obregon"
        val service = WeatherService(this)
        val weather = service.getWeather(city)
        val time = LocalTime.now().hour

        setGreeting()

        tvCity.text = city
        tvTemperature.text = "${weather.temperature}°"
        tvWeather.text = weather.weather
        ivWeather.setImageResource(service.getWeatherIcon(weather.weather))
    }

    override fun onResume() {
        super.onResume()
        setGreeting()
    }

    private fun setGreeting() {
        val time = LocalTime.now().hour
        tvGreeting.text = when (time) {
            in 5..11 -> getString(R.string.good_morning)
            in 12..19 -> getString(R.string.good_afternoon)
            else -> getString(R.string.good_evening)
        }
    }
}