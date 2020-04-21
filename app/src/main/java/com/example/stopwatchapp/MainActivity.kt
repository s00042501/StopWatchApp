package com.example.stopwatchapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatViewInflater
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer

class MainActivity : Activity() {

    var seconds = 0
    var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
        }

        timer()
    }
        fun timer() {
            val handler = Handler()
            val post = handler.post(object : Runnable {
                override fun run() {
                    if (running) {
                        seconds++
                    }
                    var hour = seconds / 3600
                    var minutes = (seconds % 3600) / 60
                    var sec = seconds  % 60
                    handler.postDelayed(this, 1000)
                    textView.text = "$hour:$minutes:$seconds"
                }
            })
        }
        fun btnClicked(view: View) {
            when (view.id) {
                R.id.btnStart -> {
                    running = true
                }
                R.id.btnStop -> {
                    running = false
                }
                R.id.btnReset -> {
                    running = false
                    seconds = 0
                }
            }
        }

    override fun onSaveInstanceState(outState: Bundle?){
        super.onSaveInstanceState(outState)
        outState?.putInt("seconds", seconds)
        outState?.putBoolean("running", running)
    }



}
