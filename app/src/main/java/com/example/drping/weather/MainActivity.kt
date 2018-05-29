package com.example.drping.weather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject





class MainActivity : AppCompatActivity() {

    private val url = "http://api.openweathermap.org/data/2.5/weather?q=Toulouse,Fr&APPID=a0225b6f559c37b56f210f65a26a7e52&units=metric"

    lateinit var temp_text: TextView
    lateinit var weather_text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun UpdateWeather(view: View) {
        temp_text = findViewById(R.id.temp_text)
        weather_text = findViewById(R.id.weather_text)
        val request = StringRequest(url, Response.Listener { string -> parseJsonData(string) },
                Response.ErrorListener { Toast.makeText(applicationContext, "Some error occurred!!", Toast.LENGTH_SHORT).show() })
        val rQueue = Volley.newRequestQueue(this@MainActivity)
        rQueue.add(request)
    }

    private fun parseJsonData(string: String) {
        val jobject = JSONObject(string)
        val temp = jobject.getJSONObject("main").getString("temp").toString()
        val weather = jobject.getJSONArray("weather").getJSONObject(0).getString("main").toString()
        temp_text.text = temp
        weather_text.text = weather
    }

}
