package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.data.entity.ClientMessageModel
import com.decagonhq.clads.databinding.FragmentMessagesMessageListLayoutBinding

class FragmentMessageClientAdapter(private var clientMessageList: ArrayList<ClientMessageModel>) :
    RecyclerView.Adapter<FragmentMessageClientAdapter.MessageClientViewHolder>() {

    inner class MessageClientViewHolder(val binding: FragmentMessagesMessageListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var clientName = binding.fragmentMessageClientTextView
        var clientMessageDate = binding.fragmentMessageMessageDateTextView
        var clientMessage = binding.fragmentMessageMessageContentTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageClientViewHolder {
        val binding = FragmentMessagesMessageListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageClientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageClientViewHolder, position: Int) {
        holder.clientName.text = clientMessageList[position].fullName
        holder.clientMessageDate.text = clientMessageList[position].messageDate
        holder.clientMessage.text = clientMessageList[position].clientMessage
    }

    override fun getItemCount(): Int = clientMessageList.size
}
