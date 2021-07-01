package com.decagonhq.clads.ui.view.profilemanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagonhq.clads.R
import com.decagonhq.clads.data.entity.ClientMessageModel
import com.decagonhq.clads.databinding.FragmentMessageBinding
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.FragmentMessageClientAdapter

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!
    private lateinit var clientMessageList: ArrayList<ClientMessageModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMessageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayClientMessage()

        binding.fragmentMessageClientMessageRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FragmentMessageClientAdapter(clientMessageList)
        }
    }

    private fun displayClientMessage() {
        clientMessageList = arrayListOf(
            ClientMessageModel("Ola Machiavelli", "Today", getString(R.string.fragment_message_message_text)),
            ClientMessageModel("Wilson Kent", "Yesterday", getString(R.string.fragment_message_message_text)),
            ClientMessageModel("Michael Jenkins", "2 days ago", getString(R.string.fragment_message_message_text)),
            ClientMessageModel("Jones Philips", "5 days ago", getString(R.string.fragment_message_message_text)),
            ClientMessageModel("Mubarak Yang", "A week ago", getString(R.string.fragment_message_message_text)),
        )
    }
}
