package com.decagonhq.clads.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.ActivityProfileDashboardBinding

class ProfileDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityProfileDashboardBinding.inflate(layoutInflater).root)
    }
}