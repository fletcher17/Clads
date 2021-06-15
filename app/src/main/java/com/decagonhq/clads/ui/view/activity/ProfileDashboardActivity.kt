package com.decagonhq.clads.ui.view.activity

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
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

    private lateinit var binding: ActivityProfileDashboardBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityProfileDashboardBinding.inflate(layoutInflater)
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
        navView.findViewById<Button>(R.id.profile_activity_header_edit_profile_button)
            .setOnClickListener {
                drawerLayout.closeDrawer(Gravity.LEFT)
                navController.navigate(R.id.action_dashboardFragment_to_editProfileFragment)
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
                    binding.activityProfileDashboardToolbarLayout
                        .profileActivityAppBarConstraintLayout.visibility = View.GONE
                    binding.activityProfileDashboardToolbarLayout
                        .activityProfileDashboardBottomNavigationView.visibility = View.GONE
                }
                (destination.id == R.id.editProfileFragment) -> {
                    binding.activityProfileDashboardToolbarLayout
                        .profileActivityAppBarConstraintLayout.visibility = View.GONE
                }
                (destination.id == R.id.mediaFragment) ->
                    binding.activityProfileDashboardToolbarLayout
                        .profileActivityAppBarConstraintLayout.visibility = View.GONE
                (destination.id == R.id.addClientFragment) ->
                    binding.activityProfileDashboardToolbarLayout
                        .profileActivityAppBarConstraintLayout.visibility = View.GONE
                (destination.id == R.id.clientHomeFragment) ->
                    binding.activityProfileDashboardToolbarLayout
                        .profileActivityAppBarConstraintLayout.visibility = View.GONE

                (destination.id == R.id.resourceHomeFragment) -> {
                    binding.activityProfileDashboardToolbarLayout
                        .profileActivityAppBarConstraintLayout.visibility = View.GONE
                    binding.activityProfileDashboardToolbarLayout
                        .activityProfileDashboardBottomNavigationView.visibility = View.GONE
                }
                (destination.id == R.id.resourceViewAllArticlesFragment) -> {
                    binding.activityProfileDashboardToolbarLayout
                        .profileActivityAppBarConstraintLayout.visibility = View.GONE
                    binding.activityProfileDashboardToolbarLayout
                        .activityProfileDashboardBottomNavigationView.visibility = View.GONE
                }
                (destination.id == R.id.resourceViewAllVideosFragment) -> {
                    binding.activityProfileDashboardToolbarLayout
                        .profileActivityAppBarConstraintLayout.visibility = View.GONE
                    binding.activityProfileDashboardToolbarLayout
                        .activityProfileDashboardBottomNavigationView.visibility = View.GONE
                }
                (destination.id == R.id.resourceViewIndividualVideoFragment) -> {
                    binding.activityProfileDashboardToolbarLayout
                        .profileActivityAppBarConstraintLayout.visibility = View.GONE
                    binding.activityProfileDashboardToolbarLayout
                        .activityProfileDashboardBottomNavigationView.visibility = View.GONE
                }
                (destination.id == R.id.resourceViewIndividualArticleFragment) -> {
                    binding.activityProfileDashboardToolbarLayout
                        .profileActivityAppBarConstraintLayout.visibility = View.GONE
                    binding.activityProfileDashboardToolbarLayout
                        .activityProfileDashboardBottomNavigationView.visibility = View.GONE
                }
                else -> {
                    binding.activityProfileDashboardToolbarLayout
                        .profileActivityAppBarConstraintLayout.visibility = View.VISIBLE
                    binding.activityProfileDashboardToolbarLayout
                        .activityProfileDashboardBottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    override fun onOptionsItemSelected(item: MenuItem): Boolean = item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
}
