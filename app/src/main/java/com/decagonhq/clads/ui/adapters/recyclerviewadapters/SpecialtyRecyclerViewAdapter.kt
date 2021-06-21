package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.data.entity.Specialty
import com.decagonhq.clads.databinding.FragmentProfileSpecialtyItemBinding
import com.decagonhq.clads.utils.SpecialtyDiffCallback

class SpecialtyRecyclerViewAdapter :
    RecyclerView.Adapter<SpecialtyRecyclerViewAdapter.SpecialtyViewHolder>() {

    private var specialtyList = arrayListOf<Specialty>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtyViewHolder {
        val viewBinding = FragmentProfileSpecialtyItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SpecialtyViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: SpecialtyViewHolder, position: Int) {
        val specialty = specialtyList[position]
        holder.bind(specialty)
    }

    override fun getItemCount(): Int {
        return specialtyList.size
    }

    class SpecialtyViewHolder(private val itemBinding: FragmentProfileSpecialtyItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(specialty: Specialty) = with(itemBinding) {
            fragmentProfileSpecialtyCheckbox.text = specialty.name
            fragmentProfileSpecialtyCheckbox.isChecked = specialty.checked
        }
    }

    fun setData(newSpecialtyList: ArrayList<Specialty>) {
        val diffCallBack = SpecialtyDiffCallback(specialtyList, newSpecialtyList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        specialtyList.clear()
        specialtyList.addAll(newSpecialtyList)
        diffResult.dispatchUpdatesTo(this)
    }
}
