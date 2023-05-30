package com.example.temperature

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val temperatureText: TextView = findViewById(R.id.temperatureText)
        //val refreshButton: Button = findViewById(R.id.button_refresh)

        val queue = Volley.newRequestQueue(this)
        val url = "http://147.91.161.210:3000/temperature"

        fun fetchTemperature() {
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    temperatureText.text = "Response: $response"
                },
                { error ->
                    Log.e("Volley", "Error occurred", error)
                    temperatureText.text = "That didn't work!"
                })

            queue.add(stringRequest)
        }

        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                fetchTemperature()
                handler.postDelayed(this, 1000) // Ponovno pokretanje zadatka nakon 1 sekunde
            }
        }

        fetchTemperature()

       //refreshButton.setOnClickListener {
          //  fetchTemperature() // fetch again when button is clicked
        //}

        handler.postDelayed(runnable, 1000) // Pokretanje zadatka odmah i ponovno pokretanje svake sekunde
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable) // Uklanjanje zadatka iz reda čekanja Handler-a
    }
}
