package com.decagonhq.clads

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Start home activity
        startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        // close splash activity
        finish()
    }
}
