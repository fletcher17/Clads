package com.decagonhq.clads.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.decagonhq.clads.databinding.ActivityProfileDashboardBinding

class ProfileDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityProfileDashboardBinding.inflate(layoutInflater).root)
        supportActionBar?.hide()
    }
}
