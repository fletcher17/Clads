package com.decagonhq.clads.ui.view.activity

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.ActivityProfileDashboardBinding

class ProfileDashboardActivity : AppCompatActivity() {

    private var _binding: ActivityProfileDashboardBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.activityProfileDashboardToolbarLayout.profileActivityAppBarToolbar)

        setUpBottomNavigationView()

        // setting up drawer layout
        val drawerLayout = binding.drawerLayout
        val navView = binding.activityProfileDashboardNavigationView.getHeaderView(0)
        navView.findViewById<ImageView>(R.id.profile_activity_header_dashboard_close_header_icon)
            .setOnClickListener {
                drawerLayout.closeDrawer(Gravity.LEFT)
            }
    }

    // setting up bottom navigation
    private fun setUpBottomNavigationView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.activityProfileDashboardNavigationView.setupWithNavController(navController)
        binding.activityProfileDashboardToolbarLayout.activityProfileDashboardBottomNavigationView.setupWithNavController(
            navController
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when {
                (destination.id == R.id.messageFragment) -> {
                    setNavigationBarItems(true)
                }
                else -> {
                    setNavigationBarItems(false)
                }
            }
        }
    }

    private fun setNavigationBarItems(requiredDestination: Boolean) {
        binding.apply {
            if (requiredDestination) {
                activityProfileDashboardToolbarLayout.profileActivityHeaderAppBarImageView.visibility =
                    View.GONE
                activityProfileDashboardToolbarLayout.profileActivityHeaderAppBarTitleTextView.visibility =
                    View.GONE
                activityProfileDashboardToolbarLayout.profileActivityAppBarNotificationIcon.visibility =
                    View.GONE
            } else {
                activityProfileDashboardToolbarLayout.profileActivityHeaderAppBarImageView.visibility =
                    View.VISIBLE
                activityProfileDashboardToolbarLayout.profileActivityHeaderAppBarTitleTextView.visibility =
                    View.VISIBLE
                activityProfileDashboardToolbarLayout.profileActivityAppBarNotificationIcon.visibility =
                    View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}
