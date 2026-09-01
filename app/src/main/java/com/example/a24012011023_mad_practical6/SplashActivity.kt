package com.example.a24012011023_mad_practical6

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() , Animation.AnimationListener{
    lateinit var guniFrameAnimation: AnimationDrawable
    lateinit var imgLogo: ImageView
    lateinit var guniAnimation: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgLogo=findViewById<ImageView>(R.id.img_logo)
        imgLogo.setBackgroundResource(R.drawable.uvpce_animation_list)
        guniFrameAnimation=imgLogo.background as AnimationDrawable

        guniAnimation= AnimationUtils.loadAnimation(this,R.anim.twin_animation)
        guniAnimation.setAnimationListener(this)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus){
            guniFrameAnimation.start()
            imgLogo.startAnimation(guniAnimation)
        }
        else{
            guniFrameAnimation.stop()
        }
    }

    override fun onAnimationEnd(p0: Animation?) {
        Intent(this, MainActivity::class.java).also { startActivity(it) }
    }

    override fun onAnimationRepeat(p0: Animation?) {

    }

    override fun onAnimationStart(p0: Animation?) {

    }
}