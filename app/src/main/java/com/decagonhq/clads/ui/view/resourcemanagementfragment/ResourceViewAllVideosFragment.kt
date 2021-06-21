package com.decagonhq.clads.ui.view.resourcemanagementfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.decagonhq.clads.databinding.FragmentResourceViewAllVideosBinding
import com.decagonhq.clads.utils.Interface.ExoPlayerVideosClickListener

class ResourceViewAllVideosFragment : Fragment(), ExoPlayerVideosClickListener {

    private var _binding: FragmentResourceViewAllVideosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResourceViewAllVideosBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        return view
    }

    override fun onClickVideo(position: Int) {
        Toast.makeText(requireContext(), "i got clicked", Toast.LENGTH_LONG).show()
    }
}
