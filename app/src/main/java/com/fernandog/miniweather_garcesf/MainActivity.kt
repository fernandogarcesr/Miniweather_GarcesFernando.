package com.fernandog.miniweather_garcesf

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.ImageView
import utilices.WeatherService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
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
        val temperature = intent.getIntExtra("temperature", 24)
        val service = WeatherService(this)
        val weatherDescription = intent.getStringExtra("weather") ?: getString(R.string.sunny)

        tvGreeting.text = "Buenos días"
        tvCity.text = city
        tvTemperature.text = "$temperature°"
        tvWeather.text = weatherDescription
        ivWeather.setImageResource(service.getWeatherIcon(weatherDescription))
    }
}