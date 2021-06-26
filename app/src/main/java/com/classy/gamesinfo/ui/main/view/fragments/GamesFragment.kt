package com.classy.gamesinfo.ui.main.view.fragments

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.classy.gamesinfo.databinding.FragmentGamesBinding
import com.classy.gamesinfo.ui.main.viewmodel.GamesViewModel


class GamesFragment : Fragment() {

    private val gamesViewModel: GamesViewModel by viewModels()
    private var _binding: FragmentGamesBinding? = null

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
        // 0 - date
        // 1 - name or genre

        var filter: String? = null
        filter = if (inputType == 1) {
            "release_date:$text"
        } else {
            "genres:$text"
        }
    }

    private fun setupUI() {
        binding.gamesChipGroup.setOnCheckedChangeListener { chipGroup, _ ->
            if (chipGroup.checkedChipId > 0) {
                // If a checked chip exists, show the edit text
                binding.gamesEDTEditText.visibility = VISIBLE
                if (resources.getResourceName(chipGroup.checkedChipId).contains("Date")) {
                    // if the checked chip is release date - change input accordingly
                    binding.gamesEDTEditText.inputType = InputType.TYPE_CLASS_DATETIME
                } else {
                    binding.gamesEDTEditText.inputType = InputType.TYPE_CLASS_TEXT
                }
            }

            binding.gamesEDTEditText.setOnClickListener {
                val inputType: Int = if (binding.gamesEDTEditText.inputType == InputType.TYPE_CLASS_DATETIME) {
                    0
                } else {
                    1
                }
                setupObservers(inputType, binding.gamesEDTEditText.text.toString())
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("FFFF", "onDestroyView()")
        _binding = null
    }
}