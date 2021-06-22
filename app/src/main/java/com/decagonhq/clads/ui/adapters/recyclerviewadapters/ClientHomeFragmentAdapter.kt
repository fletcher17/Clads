package com.decagonhq.clads.ui.adapters.recyclerviewadapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.data.entity.ClientListAddClientModel
import com.decagonhq.clads.databinding.FragmentClientListRecyclerViewLayoutLookBinding
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class ClientHomeFragmentAdapter(private var clientDetailsList: ArrayList<ClientListAddClientModel>) :
    RecyclerView.Adapter<ClientHomeFragmentAdapter.ClientHomeFragmentViewHolder>() {
    private var context: Context? = null

    fun setClientHomeAdapterList(clientDetailsList: ArrayList<ClientListAddClientModel>) {
        this.clientDetailsList = clientDetailsList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClientHomeFragmentViewHolder {

        if (context == null)
            context = parent.context

        // INFLATING THE LAYOUT FOR THIS FRAGMENT
        val binding = FragmentClientListRecyclerViewLayoutLookBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ClientHomeFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientHomeFragmentViewHolder, position: Int) {

        // BIND ALL VIEWS TO THEIR VARIOUS POSITION
        holder.bindAllViews(position)
    }

    override fun getItemCount(): Int {
        return clientDetailsList.size
    }

    private fun getClientNamesInitials(clientName: String): String {
        return clientName[0].toUpperCase().toString()
    }

    // SETTING DIFFERENT BACKGROUNDS COLORS FOR THE BACKGROUND PROFILE IMAGE
    private fun changeClientProfileBackgroundColor(context: Context): Drawable? {
        val colorDrawables = arrayOf(
            R.drawable.fragment_client_list_background_color_drawable_1,
            R.drawable.fragment_client_list_background_color_drawable_2,
            R.drawable.fragment_client_list_background_color_drawable_3,
            R.drawable.fragment_client_list_background_color_drawable_4,
            R.drawable.fragment_client_list_background_color_drawable_5,
            R.drawable.fragment_client_list_background_color_drawable_6
        )
        return ContextCompat.getDrawable(
            context,
            colorDrawables[(Math.random() * 5).roundToInt()]
        )
    }

    // VIEW HOLDER FOR THE RECYCLER VIEW
    inner class ClientHomeFragmentViewHolder(private var binding: FragmentClientListRecyclerViewLayoutLookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindAllViews(position: Int) {
            val currentClientListItem = clientDetailsList[position]

            // BINDING ALL VIEWS
            binding.apply {
                fragmentClientHomeListRecyclerLookClientFirstNameInitialTextview.text =
                    getClientNamesInitials(currentClientListItem.clientFirstName)
                fragmentClientHomeListRecyclerLookClientLastNameInitialsTextview.text =
                    getClientNamesInitials(currentClientListItem.clientLastName)
                fragmentClientHomeListFirstNameTextview.text = currentClientListItem.clientFirstName
                fragmentClientHomeListLastNameTextview.text = currentClientListItem.clientLastName
                fragmentClientHomeListRecyclerLookClientLocation.text =
                    currentClientListItem.clientLocation

                // ROUNDED PROFILE IMAGE DRAWABLE
                fragmentClientHomeImageProfileColorBackground.background =
                    changeClientProfileBackgroundColor(binding.root.context)
            }
        }
    }
}
