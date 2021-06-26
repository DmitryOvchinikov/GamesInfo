package com.classy.gamesinfo.ui.main.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.classy.gamesinfo.R
import com.classy.gamesinfo.databinding.ActivityMainBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

//TODO: To get no duplicate loading issues (e.g. pressing home, than coming back to the activity) with koin and DI: https://stackoverflow.com/questions/56289929/how-to-use-koin-in-multiple-module
//TODO: Save the State of the fragments so the application won't call the API everytime the user switches a fragment, onSaveInstanceState() + local persistence (Room?)
//TODO: delete mySharedPref?

class MainActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var viewBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        setupNavigation()

    }

    private fun setupNavigation() {
        val navView: BottomNavigationView = viewBinding.navView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_news,
                R.id.navigation_reviews,
                R.id.navigation_videos,
                R.id.navigation_games,
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}