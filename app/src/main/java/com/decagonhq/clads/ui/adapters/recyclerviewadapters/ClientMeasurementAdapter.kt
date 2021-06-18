package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.utils.ClientMeasurementData
import com.decagonhq.clads.utils.Interface.ClientMeasurementClickListener

class ClientMeasurementAdapter(
    var listOfClientMeasurements: ArrayList<ClientMeasurementData>,
    var clickItem: ClientMeasurementClickListener,
    var editdetails: ClientMeasurementClickListener
) : RecyclerView.Adapter<ClientMeasurementAdapter.ClientMeasurementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientMeasurementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.staggard_recyclerview_layout, parent, false)

        return ClientMeasurementViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClientMeasurementViewHolder, position: Int) {
        holder.nameOfMeasurement.text = listOfClientMeasurements[position].nameOfMeasurement
        holder.value.text = listOfClientMeasurements[position].valueOfMeasurement

        holder.cancel.setOnClickListener {
            clickItem.onClickItem(listOfClientMeasurements[position], position)
        }

        holder.nameOfMeasurement.setOnClickListener {
            editdetails.editMeasurement(listOfClientMeasurements[position], position)
        }
    }

    override fun getItemCount(): Int {
        return listOfClientMeasurements.size
    }

    inner class ClientMeasurementViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var nameOfMeasurement: TextView
        var value: TextView
        var cancel: ImageView

        init {
            nameOfMeasurement = itemView.findViewById(R.id.staggard_recyclerview_name_text_view)
            value = itemView.findViewById(R.id.staggard_recyclerview_value_text_view)
            cancel = itemView.findViewById(R.id.staggard_recyclerview_cancel_icon)
        }

        /**item position in recyclerview */
        override fun onClick(v: View?) {
            val position: Int = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                clickItem.onClickItem(listOfClientMeasurements[position], position)
            }
        }
    }

    fun setList(listOfClient: ClientMeasurementData) {
        this.listOfClientMeasurements.add(listOfClient)
        notifyDataSetChanged()
    }
}
