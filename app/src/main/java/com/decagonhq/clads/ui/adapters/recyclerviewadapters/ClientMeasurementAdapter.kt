package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.databinding.StaggardRecyclerviewLayoutBinding
import com.decagonhq.clads.utils.ClientMeasurementData
import com.decagonhq.clads.utils.helpers.ClientMeasurementClickListener

class ClientMeasurementAdapter(
    var listOfClientMeasurements: ArrayList<ClientMeasurementData>,
    var clickItem: ClientMeasurementClickListener,
    var editdetails: ClientMeasurementClickListener
) : RecyclerView.Adapter<ClientMeasurementAdapter.ClientMeasurementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientMeasurementViewHolder {
        val view = StaggardRecyclerviewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ClientMeasurementViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClientMeasurementViewHolder, position: Int) {
        val clientData = listOfClientMeasurements[position]

        holder.bind(clientData, clickItem)
    }

    override fun getItemCount(): Int {
        return listOfClientMeasurements.size
    }

    inner class ClientMeasurementViewHolder(private val binding: StaggardRecyclerviewLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(clientMeasurementData: ClientMeasurementData, clickListener: ClientMeasurementClickListener) {
            binding.staggardRecyclerviewNameTextView.text = clientMeasurementData.nameOfMeasurement
            binding.staggardRecyclerviewValueTextView.text = clientMeasurementData.valueOfMeasurement

            binding.staggardRecyclerviewNameTextView.setOnClickListener {
                clickListener.editMeasurement(clientMeasurementData)
            }

            binding.staggardRecyclerviewCancelIcon.setOnClickListener {
                clickListener.onClickItem(clientMeasurementData)
            }
        }
    }

    fun setList(listOfClient: ClientMeasurementData) {
        this.listOfClientMeasurements.add(listOfClient)
        notifyDataSetChanged()
    }
}
