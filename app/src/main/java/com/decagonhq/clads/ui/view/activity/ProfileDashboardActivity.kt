package com.decagonhq.clads.ui.view.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.data.local.AppSharedPreference
import com.decagonhq.clads.databinding.ActivityProfileDashboardBinding
import com.decagonhq.clads.ui.viewmodel.EditProfileFragmentViewModel
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileDashboardBinding
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var viewModelUsedToUpdateViewsInAccountTabFragment: EditProfileFragmentViewModel
    @Inject
    lateinit var sharedPref: AppSharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityProfileDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.activityProfileDashboardToolbarLayout.profileActivityAppBarToolbar)

        // initializing the drawer layout
        drawerLayout = binding.drawerLayout

        // initializing viewModelUsedToUpdateViewsInAccountTabFragment
        viewModelUsedToUpdateViewsInAccountTabFragment = ViewModelProvider(this).get(
            EditProfileFragmentViewModel::class.java
        )

        setUpBottomNavigationView()

        // setting up drawer layout
        drawerLayout = binding.drawerLayout
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

        // Initializing the navigationView variable
        navigationView = binding.activityProfileDashboardNavigationView
        // This is used to set onclick listener to each of the menu items inside the navigation drawer
        navigationView.setNavigationItemSelectedListener() {
            when (it.itemId) {
                R.id.clientHomeFragment -> {
                    navController.navigate(R.id.clientHomeFragment)
                    drawerLayout.closeDrawer(Gravity.LEFT)
                    return@setNavigationItemSelectedListener true
                }
                R.id.resourceHomeFragment -> {
                    navController.navigate(R.id.resourceHomeFragment)
                    drawerLayout.closeDrawer(Gravity.LEFT)
                    return@setNavigationItemSelectedListener true
                }
                R.id.subscription -> {
                    return@setNavigationItemSelectedListener true
                }
                // When the item clicked is logout
                R.id.logout -> {
                    // Using a dialog to ask the user for confirmation before logging out
                    val confirmationDialog = AlertDialog.Builder(this)
                    confirmationDialog.setMessage("You are about to logout. \nPress \"YES\" to continue or \"NO\" to cancel.")
                    confirmationDialog.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                        // First clear the Shared preference, so that the authentication token stored in it will be deleted
                        sharedPref.clearSharedPref()
                        // After clearing the shared preference, navigate the user back to the landing screen which is the MainActivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        // Use Toast to notify the user that they are being logged out
                        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                        // Finish the current activity so that the user can not use the back button to come back to this current screen
                        finish()
                    }
                    confirmationDialog.setNegativeButton(
                        "No"
                    ) { _: DialogInterface, _: Int ->
                    }
                    confirmationDialog.create().show()
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    return@setNavigationItemSelectedListener true
                }
            }
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
//                    binding.activityProfileDashboardToolbarLayout
//                        .profileActivityAppBarConstraintLayout.visiblity = View.GONE
                }
                (destination.id == R.id.mediaFragment) -> {
                    binding.activityProfileDashboardToolbarLayout.profileActivityHeaderAppBarTitleTextView.text =
                        getString(R.string.photo_gallery_str)
                    binding.activityProfileDashboardToolbarLayout.profileActivityHeaderAppBarImageView.visibility = View.GONE
                    binding.activityProfileDashboardToolbarLayout.profileActivityAppBarNotificationIcon.visibility = View.GONE
                }

                (destination.id == R.id.mediaDisplayPictureFragment) -> {
                    binding.activityProfileDashboardToolbarLayout.profileActivityHeaderAppBarTitleTextView.visibility = View.VISIBLE
                    binding.activityProfileDashboardToolbarLayout.profileActivityHeaderAppBarImageView.visibility = View.GONE
                    binding.activityProfileDashboardToolbarLayout.profileActivityAppBarNotificationIcon.visibility = View.GONE
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
