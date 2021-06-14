package com.decagonhq.clads.ui.adapters.viewpagersadapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.decagonhq.clads.ui.view.profilemanagementfragments.ProfileAccountFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.ProfilePaymentMethodTabFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.ProfileSecurityTabFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.ProfileSpecialtyTabFragment

class EditProfileFragmentViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> ProfileSpecialtyTabFragment()
            2 -> ProfilePaymentMethodTabFragment()
            3 -> ProfileSecurityTabFragment()
            else -> ProfileAccountFragment()
        }
    }

    companion object {
        private const val NUM_PAGES = 4
    }
}
