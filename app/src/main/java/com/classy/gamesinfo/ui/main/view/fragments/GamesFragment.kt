package com.classy.gamesinfo.ui.main.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.classy.gamesinfo.databinding.FragmentGamesBinding
import com.classy.gamesinfo.ui.main.viewmodel.GamesViewModel

class GamesFragment : Fragment() {

    private lateinit var gamesViewModel: GamesViewModel
    private var _binding: FragmentGamesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        gamesViewModel =
            ViewModelProvider(this).get(GamesViewModel::class.java)

        _binding = FragmentGamesBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textHome
//        newsViewModel.text.observe(viewLifecycleOwner, {
//            textView.text = it
//        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}