package com.example.a24012011023_mad_practical6

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var alarmFrameAnimation: AnimationDrawable
    lateinit var alarmImg: ImageView
    lateinit var heartFrameAnimation: AnimationDrawable
    lateinit var heartImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        alarmImg=findViewById<ImageView>(R.id.imageView)
        alarmImg.setBackgroundResource(R.drawable.alarm_animation_list)
        alarmFrameAnimation=alarmImg.background as AnimationDrawable

        heartImg=findViewById<ImageView>(R.id.heart)
        heartImg.setBackgroundResource(R.drawable.heart_animation_list)
        heartFrameAnimation=heartImg.background as AnimationDrawable
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        alarmFrameAnimation.start()
        heartFrameAnimation.start()
    }
}