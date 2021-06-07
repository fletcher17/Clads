package com.decagonhq.clads.ui.view.cliientmanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentClientMeasurementTabBinding

class ClientMeasurementTabFragment : Fragment() {

    var _binding: FragmentClientMeasurementTabBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentClientMeasurementTabBinding.inflate(inflater, container, false)
        return binding.root
    }
}
