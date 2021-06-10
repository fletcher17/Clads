package com.decagonhq.clads.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.databinding.FragmentProfileSpecialtyItemBinding
import com.decagonhq.clads.models.Specialty

class ProfileSpecialtyFragmentRecyclerViewAdapter :
    RecyclerView.Adapter<ProfileSpecialtyFragmentRecyclerViewAdapter.SpecialtyViewHolder>() {

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

    fun populateList(list: ArrayList<Specialty>) {
        this.specialtyList = list
        notifyDataSetChanged()
    }
}