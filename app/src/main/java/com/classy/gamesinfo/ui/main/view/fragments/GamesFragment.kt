package com.classy.gamesinfo.ui.main.view.fragments

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.classy.gamesinfo.data.model.gamespot.Franchise
import com.classy.gamesinfo.data.model.gamespot.GamesJsonAPI
import com.classy.gamesinfo.data.model.gamespot.Genre
import com.classy.gamesinfo.data.model.gamespot.ResultGame
import com.classy.gamesinfo.databinding.FragmentGamesBinding
import com.classy.gamesinfo.ui.main.adapter.GamesAdapter
import com.classy.gamesinfo.ui.main.viewmodel.GamesViewModel
import com.classy.gamesinfo.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList


class GamesFragment : Fragment() {

    private var _binding: FragmentGamesBinding? = null
    private val gamesViewModel: GamesViewModel by viewModel()
    private lateinit var gamesAdapter: GamesAdapter

    private val INPUT_TYPE_DATE: Int = 0
    private val INPUT_TYPE_TEXT: Int = 1
    private val INPUT_TYPE_NORMAL: Int = 2
    private val INPUT_TYPE_LONG_MESSAGE: Int = 3

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGamesBinding.inflate(inflater, container, false)

        setupUI();

        return binding.root
    }

    private fun setupObservers(inputType: Int, text: String) {
        text.replace(' ', '%') //replacing white space so the url will work with the api call

        var isTextAGenre= false
        var isTextAFranchise= false
        var sortBy = "release_date:asc"

        val filter: String = when (inputType) {
            INPUT_TYPE_DATE -> {
                val calendar: Calendar = Calendar.getInstance()
                val currentYear = calendar.get(Calendar.YEAR)
                val currentMonth = calendar.get(Calendar.MONTH)+1
                val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
                "release_date:$text-01-01|$currentYear-$currentMonth-$currentDay"

            }
            INPUT_TYPE_TEXT -> {
                isTextAGenre = true
                "genres:$text"
            }
            INPUT_TYPE_LONG_MESSAGE -> {
                isTextAFranchise = true
                "franchises:$text"
            }
            else -> {
                Log.d("FFFF", "" + text)
                "name:$text"
            }
        }

        gamesViewModel.getGames("json", sortBy, 50, gamesAdapter.itemCount, filter)
            .observe(viewLifecycleOwner, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            binding.gamesLSTRecyclerView.visibility = View.VISIBLE
                            binding.gamesBARProgressBar.visibility = View.GONE
                            resource.data?.let { games ->
                                Log.d("FFFF", "isTextAGenre: $isTextAGenre")
                                Log.d("FFFF", "isTextAFranchise: $isTextAFranchise")
                                if (isTextAGenre) {
                                    val genre= Genre(filter.removePrefix("genres:"))
                                    Log.d("FFFF", "" + genre.name)
                                    addGamesByGenreToAdapter(games, genre)
                                } else if (isTextAFranchise) {
                                    val franchise = Franchise(filter.removePrefix("franchises"))
                                    addGamesByFranchiseToAdapter(games, franchise)
                                } else {
                                    addGamesToAdapter(games)
                                }
                            }
                            Log.d("FFFF", "getGames(): SUCCESS")
                        }
                        Status.ERROR -> {
                            binding.gamesLSTRecyclerView.visibility = View.VISIBLE
                            binding.gamesBARProgressBar.visibility = View.GONE
                            Log.d("FFFF", "getGames(): " + it.message.toString())
                        }
                        Status.LOADING -> {
                            binding.gamesBARProgressBar.visibility = View.VISIBLE
                            binding.gamesLSTRecyclerView.visibility = View.GONE
                            Log.d("FFFF", "getGames(): LOADING")
                        }
                    }
                }
            })
    }

    private fun addGamesToAdapter(gamesAPI: GamesJsonAPI) {
        gamesAdapter.apply {
            addGames(gamesAPI.results as ArrayList<ResultGame>)
            notifyDataSetChanged()
        }
    }

    private fun addGamesByGenreToAdapter(gamesAPI: GamesJsonAPI, genre: Genre) {
        gamesAdapter.apply {
            addGamesByGenre(gamesAPI.results as ArrayList<ResultGame>, genre)
            notifyDataSetChanged()
        }
    }

    private fun addGamesByFranchiseToAdapter(gamesAPI: GamesJsonAPI, franchise: Franchise) {
        gamesAdapter.apply {
            addGamesByFranchise(gamesAPI.results as ArrayList<ResultGame>, franchise)
            notifyDataSetChanged()
        }
    }

    private fun setupUI() {
        binding.gamesLSTRecyclerView.layoutManager = LinearLayoutManager(activity)
        gamesAdapter = GamesAdapter(arrayListOf())
        binding.gamesLSTRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.gamesLSTRecyclerView.context,
                (binding.gamesLSTRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.gamesLSTRecyclerView.adapter = gamesAdapter

        binding.gamesChipGroup.setOnCheckedChangeListener { chipGroup, _ ->
            if (chipGroup.checkedChipId > 0) {
                // If a checked chip exists, show the edit text
                binding.gamesEDTEditText.visibility = View.VISIBLE
                if (resources.getResourceName(chipGroup.checkedChipId).contains("Date")) {
                    binding.gamesEDTEditText.inputType = InputType.TYPE_CLASS_DATETIME
                } else if (resources.getResourceName(chipGroup.checkedChipId).contains("genre")) {
                    binding.gamesEDTEditText.inputType = InputType.TYPE_CLASS_TEXT
                } else if (resources.getResourceName(chipGroup.checkedChipId).contains("franchise")) {
                    binding.gamesEDTEditText.inputType = InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE
                } else {
                    binding.gamesEDTEditText.inputType = InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT
                }
            }
        }
        binding.gamesEDTEditText.setOnEditorActionListener { _, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                val inputType: Int
                if (binding.gamesEDTEditText.inputType == InputType.TYPE_CLASS_DATETIME) {
                    inputType = INPUT_TYPE_DATE
                } else if (binding.gamesEDTEditText.inputType == InputType.TYPE_CLASS_TEXT) {
                    inputType = INPUT_TYPE_TEXT
                } else if (binding.gamesEDTEditText.inputType == InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE) {
                    inputType = INPUT_TYPE_LONG_MESSAGE
                } else {
                    inputType = INPUT_TYPE_NORMAL
                }
                setupObservers(inputType, binding.gamesEDTEditText.text.toString())
                true
            }
            false
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("FFFF", "onDestroyView()")
        _binding = null
    }
}