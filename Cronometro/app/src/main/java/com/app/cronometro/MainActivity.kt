package com.app.cronometro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.app.cronometro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var running: Boolean = false
    var numberPause: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        with(binding) {
            buttonStart.setOnClickListener { startChronometer() }
            buttonPause.setOnClickListener { pauseChronometer() }
            buttonZero.setOnClickListener { zeroChronometer() }
        }
    }

    private fun startChronometer() {
        if (!running) {
            binding.chronometer.apply {
                base = SystemClock.elapsedRealtime() - numberPause
                start()
            }
            running = true
        }
    }

    private fun pauseChronometer() {
        if (running) {
            binding.chronometer.stop()
            numberPause = SystemClock.elapsedRealtime() - binding.chronometer.base
            running = false
        }
    }

    private fun zeroChronometer() {
        binding.chronometer.base = SystemClock.elapsedRealtime()
        numberPause = 0
    }
}
