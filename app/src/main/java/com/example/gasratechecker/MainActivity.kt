package com.example.gasratechecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.TextView
import java.math.BigDecimal
import java.math.RoundingMode
import java.sql.Time

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var startTime = 0L
    var stopTime = 0L

    fun start(view: View) {
        startTime = timer()
        val time = findViewById<TextView>(R.id.hello)
        time.text = "0"
    }
    fun stop(view: View){
        stopTime = timer()
        var elapsedTime: Double  = (stopTime-startTime)/1000.toDouble()
        val time = findViewById<TextView>(R.id.hello)
        val normMeter = findViewById<TextView>(R.id.normal_meter_gas_rate)
        time.text = elapsedTime.toString()
        var gasUsageM2 = BigDecimal(0.01/elapsedTime*3600).setScale(2,RoundingMode.HALF_EVEN)
        var gasUsageKw =  BigDecimal(10.77*gasUsageM2).setScale(2, RoundingMode.HALF_EVEN)
        normMeter.text = gasUsageM2.toString()
    }

}

fun timer() :Long{
    return SystemClock.elapsedRealtime()
}



