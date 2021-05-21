package com.classy.gamesinfo.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlin.collections.ArrayList
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.classy.gamesinfo.data.api.ApiHelper
import com.classy.gamesinfo.data.api.RetrofitBuilder
import com.classy.gamesinfo.data.model.Game
import com.classy.gamesinfo.data.model.IgdbAccess
import com.classy.gamesinfo.data.model.MySharedPreferences
import com.classy.gamesinfo.databinding.ActivityMainBinding
import com.classy.gamesinfo.ui.base.ViewModelFactory
import com.classy.gamesinfo.ui.main.adapter.MainAdapter
import com.classy.gamesinfo.ui.main.viewmodel.MainViewModel
import com.classy.gamesinfo.utils.Status

//TODO: create some sort of manager (same kind as AlarmManager) to call the authentication API everytime the access token expires.
//TODO: Figure out how to implement the cover images onto the recyclerview adapter

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter

    //ViewBinding
    private lateinit var viewBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        setupViewModel()
        setupUI()
        setupObservers()


    }

    private fun setupObservers() {

        val access = MySharedPreferences.instance.getIgdbAccess()
        Log.d("FFFF", "access from SP: ${access.access_token}")

        if (access.access_token == "") {
            mainViewModel.authenticate().observe(this, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            val token: IgdbAccess = resource.data!!
                            MySharedPreferences.instance.putIgdbAccess(token)
                            Log.d("FFFF", "authenticate():$token oof")
                        }
                        Status.ERROR -> {
                            Log.d("FFFF", "authenticate(): " + it.message.toString())
                        }
                        Status.LOADING -> {
                            Log.d("FFFF", "authenticate(): LOADING")
                        }
                    }
                }
            })
        }

        mainViewModel.getAllGames().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        viewBinding.mainLSTRecyclerView.visibility = View.VISIBLE
                        viewBinding.mainBARProgressBar.visibility = View.GONE
                        resource.data?.let { games ->
                            retrieveList(games)
                        }
                        Log.d("FFFF", "getAllGames(): SUCCESS")
                    }
                    Status.ERROR -> {
                        viewBinding.mainLSTRecyclerView.visibility = View.VISIBLE
                        viewBinding.mainBARProgressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        Log.d("FFFF", "getAllGames(): " + it.message.toString())
                    }
                    Status.LOADING -> {
                        viewBinding.mainBARProgressBar.visibility = View.VISIBLE
                        viewBinding.mainLSTRecyclerView.visibility = View.GONE
                        Log.d("FFFF", "getAllGames(): LOADING")
                    }
                }
            }
        })
    }

    private fun setupUI() {
        viewBinding.mainLSTRecyclerView.layoutManager = LinearLayoutManager(this)
        mainAdapter = MainAdapter(arrayListOf())
        viewBinding.mainLSTRecyclerView.addItemDecoration(
            DividerItemDecoration(
                viewBinding.mainLSTRecyclerView.context,
                (viewBinding.mainLSTRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        viewBinding.mainLSTRecyclerView.adapter = mainAdapter
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.SERVICE_SERVICE))
        ).get(MainViewModel::class.java)
    }

    private fun retrieveList(games: ArrayList<Game>) {
        mainAdapter.apply {
            Log.d("FFFF", "retrieveList(): $games")
            addGames(games)
            notifyDataSetChanged()
        }
    }
}