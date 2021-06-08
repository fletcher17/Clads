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
import com.decagonhq.clads.databinding.FragmentClientHomeBinding
import com.decagonhq.clads.models.ClientListAddClientModel
import com.decagonhq.clads.ui.ClientHomeFragmentAdapter

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
        displayFakeData()
        swapPlaceHolderWithRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SET ONCLICK LISTENER ON CLIENT HOME FRAGMENT ADD CLIENT BUTTON
        binding.clientHomeScreenAddClientFab.setOnClickListener {
            findNavController().navigate(R.id.action_clientHomeFragment_to_addClientFragment)
        }
    }

    private fun displayFakeData() {
        clientHomeArrayList.add(ClientListAddClientModel("Bawo", "Amewieye", "Delta"))
        clientHomeArrayList.add(ClientListAddClientModel("Emmanuel", "Akozie", "Abuja"))
        clientHomeArrayList.add(ClientListAddClientModel("Olalekan", "Fagbemi", "Benin"))
        clientHomeArrayList.add(ClientListAddClientModel("Adriel", "Nelson", "Benin"))
        clientHomeArrayList.add(ClientListAddClientModel("Rita", "Prex", "Lagos"))
        clientHomeArrayList.add(ClientListAddClientModel("Alvin", "Nelson", "Lagos"))
        clientHomeArrayList.add(ClientListAddClientModel("Rollins", "Mitchell", "Abuja"))
        clientHomeArrayList.add(ClientListAddClientModel("Daniel", "Tuoyo", "Lagos"))
        clientHomeArrayList.add(ClientListAddClientModel("Blessing", "Gbubemi", "Calabar"))
        clientHomeArrayList.add(ClientListAddClientModel("Micheal", "Ogbemi", "Imo"))
        clientHomeArrayList.add(ClientListAddClientModel("Omamofe", "Rose", "Bauchi"))
        clientHomeArrayList.add(ClientListAddClientModel("Toju", "Godwin", "Sokoto"))
        clientHomeArrayList.add(ClientListAddClientModel("Pavel", "Mogha", "Delta"))
        clientHomeArrayList.add(ClientListAddClientModel("Parker", "Ethan", "Rivers"))
        clientHomeArrayList.add(ClientListAddClientModel("Avi", "Specta", "Anambra"))
    }

    // REPLACE PLACEHOLDER / RECYCLER VIEW AND SET RECYCLER VIEW
    private fun swapPlaceHolderWithRecyclerView() {

        if (clientHomeArrayList.isEmpty()) {
            binding.clientListHomeImageTextPlaceholder.isVisible = true
        } else {

            binding.clientListScreenRecyclerView.layoutManager =
                LinearLayoutManager(requireContext())
            clientHomeFragmentAdapter = ClientHomeFragmentAdapter(clientHomeArrayList)
            binding.clientListScreenRecyclerView.adapter = clientHomeFragmentAdapter
            binding.clientListHomeImageTextPlaceholder.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
