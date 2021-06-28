package com.decagonhq.clads.utils

import androidx.recyclerview.widget.DiffUtil
import com.decagonhq.clads.data.entity.Specialty

class SpecialtyDiffCallback(
    private val oldList: ArrayList<Specialty>,
    private val newList: ArrayList<Specialty>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].name == newList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            oldList[oldItemPosition].checked != newList[newItemPosition].checked -> false
            else -> true
        }
    }
}
