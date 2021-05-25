package com.classy.gamesinfo.ui.main.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.classy.gamesinfo.databinding.FragmentVideosBinding
import com.classy.gamesinfo.ui.main.viewmodel.VideosViewModel

class VideosFragment : Fragment() {

    private lateinit var videosViewModel: VideosViewModel
    private var _binding: FragmentVideosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        videosViewModel =
            ViewModelProvider(this).get(VideosViewModel::class.java)

        _binding = FragmentVideosBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textNotifications
//        videosViewModel.text.observe(viewLifecycleOwner, {
//            textView.text = it
//        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}