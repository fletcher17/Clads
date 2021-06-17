package com.decagonhq.clads.ui.view.cliientmanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagonhq.clads.R
import com.decagonhq.clads.data.entity.ClientListAddClientModel
import com.decagonhq.clads.databinding.FragmentClientHomeBinding
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.ClientHomeFragmentAdapter

class ClientHomeFragment : Fragment() {

    private var _binding: FragmentClientHomeBinding? = null
    private val binding get() = _binding!!
    private var clientHomeArrayList: ArrayList<ClientListAddClientModel> = ArrayList()
    private lateinit var clientHomeFragmentAdapter: ClientHomeFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientHomeBinding.inflate(inflater, container, false)

        // REPLACES LAYOUT WITH A PLACEHOLDER IF CLIENT LIST IS EMPTY
        swapPlaceHolderWithRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SET ONCLICK LISTENER ON CLIENT HOME FRAGMENT ADD CLIENT BUTTON
        binding.fragmentClientHomeScreenAddClientFab.setOnClickListener {
            findNavController().navigate(R.id.action_clientHomeFragment_to_addClientFragment)
        }
    }

    // REPLACE PLACEHOLDER / RECYCLER VIEW AND SET RECYCLER VIEW
    private fun swapPlaceHolderWithRecyclerView() {

        if (clientHomeArrayList.isEmpty()) {
            binding.fragmentClientListHomeImageTextPlaceholder.isVisible = true
        } else {

            binding.fragmentClientListScreenRecyclerView.layoutManager =
                LinearLayoutManager(requireContext())
            clientHomeFragmentAdapter = ClientHomeFragmentAdapter(clientHomeArrayList)
            binding.fragmentClientListScreenRecyclerView.adapter = clientHomeFragmentAdapter
            binding.fragmentClientListHomeImageTextPlaceholder.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
