package com.classy.gamesinfo.ui.main.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.classy.gamesinfo.R
import com.classy.gamesinfo.databinding.ActivityMainBinding
import com.classy.gamesinfo.ui.main.adapter.MainAdapter
import com.classy.gamesinfo.ui.main.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

//TODO: Implement different ViewTypes into the recyclerView to differentiate between the News/Reviews/Videos/Events and Games.
//TODO: Change the API calls and the general hierarchy of classes to support the new setup
//TODO: Implement onClick in the recyclerView
//TODO: To get no duplicate loading issues (e.g. pressing home, than coming back to the activity) with koin and DI: https://stackoverflow.com/questions/56289929/how-to-use-koin-in-multiple-module
//TODO: Update the articles adapter to get an array of results or something similar to work with on the UI

class MainActivity : AppCompatActivity() {

/*    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter */

    //ViewBinding
    private lateinit var viewBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        setupNavigation()
//        setupUI()
//        setupObservers()

    }

    private fun setupNavigation() {
        val navView: BottomNavigationView = viewBinding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
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

//    private fun setupObservers() {
//
//        mainViewModel.getAllGames().observe(this, {
//            it?.let { resource ->
//                when (resource.status) {
//                    Status.SUCCESS -> {
//                        viewBinding.mainLSTRecyclerView.visibility = View.VISIBLE
//                        viewBinding.mainBARProgressBar.visibility = View.GONE
//                        resource.data?.let { games ->
//                            retrieveList(games)
//                        }
//                        Log.d("FFFF", "getAllGames(): SUCCESS")
//                    }
//                    Status.ERROR -> {
//                        viewBinding.mainLSTRecyclerView.visibility = View.VISIBLE
//                        viewBinding.mainBARProgressBar.visibility = View.GONE
//                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                        Log.d("FFFF", "getAllGames(): " + it.message.toString())
//                    }
//                    Status.LOADING -> {
//                        viewBinding.mainBARProgressBar.visibility = View.VISIBLE
//                        viewBinding.mainLSTRecyclerView.visibility = View.GONE
//                        Log.d("FFFF", "getAllGames(): LOADING")
//                    }
//                }
//            }
//        })
//    }
//
//    private fun setupUI() {
//        viewBinding.mainLSTRecyclerView.layoutManager = LinearLayoutManager(this)
//        mainAdapter = MainAdapter(arrayListOf())
//        viewBinding.mainLSTRecyclerView.addItemDecoration(
//            DividerItemDecoration(
//                viewBinding.mainLSTRecyclerView.context,
//                (viewBinding.mainLSTRecyclerView.layoutManager as LinearLayoutManager).orientation
//            )
//        )
//        viewBinding.mainLSTRecyclerView.adapter = mainAdapter
//    }
//
//
//    private fun retrieveList(games: ArrayList<Game>) {
//        mainAdapter.apply {
//            Log.d("FFFF", "retrieveList(): $games")
//            addGames(games)
//            notifyDataSetChanged()
//        }
//    }
}