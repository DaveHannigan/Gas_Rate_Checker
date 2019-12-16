package com.example.gasratechecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    var timerStart = 0L
    var timerStop = 0L

    fun startTimer(view: View){
        timerStart = SystemClock.elapsedRealtime()
        val timerDuration = findViewById<TextView>(R.id.timer_display)
        timerDuration.text = "0"

    }

    fun stopTimer(view: View){
        timerStop = SystemClock.elapsedRealtime()
        val duration: Float = (timerStop - timerStart)/1000F

        val timerDuration = findViewById<TextView>(R.id.timer_display)
        timerDuration.text = duration.toString()

        val gasRateDisplayNormal = findViewById<TextView>(R.id.normal_meter_gas_rate)
        val cubicMetersPerHour = 0.01/duration*3600
        gasRateDisplayNormal.text = cubicMetersPerHour.toString()

    }
}
