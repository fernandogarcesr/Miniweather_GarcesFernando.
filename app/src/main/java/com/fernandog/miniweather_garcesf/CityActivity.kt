package com.fernandog.miniweather_garcesf

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import utilices.WeatherService

class CityActivity : AppCompatActivity() {

    private var citySelected: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.getInsetsController(window,window.decorView).isAppearanceLightStatusBars = false
        setContentView(R.layout.activity_city)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val service = WeatherService(this)
        val cities = service.getCities()
        citySelected = cities[0]

        val citySelector: Spinner = findViewById<Spinner>(R.id.city_selector)

        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item,
            cities
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        citySelector.adapter = adapter

        citySelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                citySelected = cities[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }



    val buttonNext = findViewById<Button>(R.id.btn_save_city)

buttonNext.setOnClickListener{
    val intent = Intent(this, MainActivity::class.java)
    intent.putExtra("city", citySelected)
    startActivity(intent)
}

}

}