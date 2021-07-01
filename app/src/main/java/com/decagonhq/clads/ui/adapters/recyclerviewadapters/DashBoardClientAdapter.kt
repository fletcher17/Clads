package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.decagonhq.clads.data.entity.ClientModel
import com.decagonhq.clads.databinding.FragmentDashboardClientListBinding

class DashBoardClientAdapter(private var clientList: ArrayList<ClientModel>) :
    RecyclerView.Adapter<DashBoardClientAdapter.DashBoardClientViewHolder>() {

    inner class DashBoardClientViewHolder(val binding: FragmentDashboardClientListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var clientName = binding.fragmentDashboardClientNameTextView
        var clientLocation = binding.fragmentDashboardClientLocationTextView
        var clientInitial = binding.fragmentDashboardUserProfileImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardClientViewHolder {
        val binding = FragmentDashboardClientListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashBoardClientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashBoardClientViewHolder, position: Int) {
        val model = clientList[position]
        holder.itemView.apply {
            with(holder) {
                with(model) {
                    val fullName = "$firstName $lastName"
                    clientName.text = fullName
                    clientLocation.text = location

                    // Generating the color and initial placeholders for client
                    val generator: ColorGenerator = ColorGenerator.MATERIAL
                    val color = generator.randomColor
                    val drawable = TextDrawable.builder().beginConfig()
                        .width(70)
                        .height(70)
                        .fontSize(30)
                        .endConfig()
                        .buildRound("${model.firstName.substring(0, 1)}${model.lastName.substring(0, 1)}", color)

                    clientInitial.setImageDrawable(drawable)
                }
            }
        }
    }

    override fun getItemCount(): Int = clientList.size
}
