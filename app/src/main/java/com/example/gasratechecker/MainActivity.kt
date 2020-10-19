package com.example.gasratechecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView

import androidx.core.widget.doOnTextChanged
import com.example.gasratechecker.databinding.ActivityMainBinding


import java.math.BigDecimal
import java.math.RoundingMode
import java.sql.Time

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var startTime :Double = 0.0
        var stopTime :Double = 0.0

        binding.buttonReset.setOnClickListener {
            binding.timerDisplay.text = "0"
            binding.editStartReading.setText("0.0")
            binding.editStopReading.setText("0.0")
            binding.normalMeterCubicMetres.text = "0"
            binding.normalMeterGasRate.text = "0"
            binding.impMeterGasRate.text = "0"
            binding.impMeterCubicFeet.text = "0"
            binding.impMeterHalfRevGasRate.text = "0"
            binding.impMeterHalfRevCubicFeet.text ="0"

        }

        binding.startButton.setOnClickListener{
            binding.timerDisplay.text = "Timer running"
            startTime = timer()
        }
        binding.stopButton.setOnClickListener {
            stopTime = timer()
            val elapsedTime = (stopTime-startTime)/1000
            binding.timerDisplay.text = elapsedTime.toString()
            binding.normalMeterCubicMetres.text = "%.2fM\u00B3/hr".format(3600/elapsedTime*0.01)
            binding.normalMeterGasRate.text = "%.2fKw/hr".format((3600/elapsedTime*0.01)*10.77)
            binding.impMeterCubicFeet.text = "%.2fft\u00B3/hr".format(3600/elapsedTime)
            binding.impMeterGasRate.text = "%.2fKw/hr".format(3600/elapsedTime*0.0283168*10.77)
            binding.impMeterHalfRevCubicFeet.text = "%.2fft\u00B3/hr".format(3600/elapsedTime/2)
            binding.impMeterHalfRevGasRate.text = "%.2fKw/hr".format(3600/elapsedTime/2*0.0283168*10.77)
        }
        binding.editStopReading.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable){}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val start = binding.editStartReading.text.toString().toDouble()
                val stop = s.toString().toDouble()
                val elapsedTime = (stopTime - startTime)/1000
               binding.digitalMeterCubicMet.text = "%.2f M\u00B3/hr".format((stop - start)/elapsedTime*3600 )
                binding.digitalMeterGasRate.text = "%.2fKw/hr".format((stop - start)/elapsedTime*3600*10.77)
            }

        })
    }
}

fun timer() :Double{
    return SystemClock.elapsedRealtime().toDouble()
}








