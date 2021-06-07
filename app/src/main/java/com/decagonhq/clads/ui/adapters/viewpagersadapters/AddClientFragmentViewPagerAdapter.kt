package com.decagonhq.clads.ui.adapters.viewpagersadapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.decagonhq.clads.ui.view.cliientmanagementfragments.ClientAccountTabFragment
import com.decagonhq.clads.ui.view.cliientmanagementfragments.ClientDeliveryAddressTabFragment
import com.decagonhq.clads.ui.view.cliientmanagementfragments.ClientMeasurementTabFragment

private const val ADD_CLIENTS_VIEW_PAGER_FRAGMENTS_COUNT = 3

class AddClientFragmentViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return ADD_CLIENTS_VIEW_PAGER_FRAGMENTS_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ClientAccountTabFragment()
            1 -> ClientMeasurementTabFragment()
            2 -> ClientDeliveryAddressTabFragment()
            else -> ClientAccountTabFragment()
        }
    }
}
