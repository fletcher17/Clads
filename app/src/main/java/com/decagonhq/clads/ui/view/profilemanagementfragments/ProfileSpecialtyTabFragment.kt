package com.decagonhq.clads.ui.view.profilemanagementfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.adapter.ProfileSpecialtyFragmentRecyclerViewAdapter
import com.decagonhq.clads.databinding.FragmentProfileSpecialtyTabBinding
import com.decagonhq.clads.models.Specialty

class ProfileSpecialtyTabFragment : Fragment() {

    var _binding: FragmentProfileSpecialtyTabBinding? = null
    val binding get() = _binding!!

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileSpecialtyTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.fragmentProfileSpecialtyRecyclerView
        val recyclerViewAdapter = ProfileSpecialtyFragmentRecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.populateList(Specialty.getDefaultSpecialityList())

        binding.fragmentProfileSpecialtyAddNewSpecialtyTextView.setOnClickListener {
            val specialtyList = Specialty.getDefaultSpecialityList()
            specialtyList.add(Specialty("Anonymous specialty", true))
            recyclerViewAdapter.populateList(specialtyList)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
