package com.decagonhq.clads.ui.view.cliientmanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentAddClientBinding
import com.decagonhq.clads.ui.adapters.viewpagersadapters.AddClientFragmentViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AddClientFragment : Fragment(), ClientAccountTabFragment.IButtonClickInterface {

    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
//    private val args: AddClientFragmentArgs by navArgs()

    // view binding
    private var _binding: FragmentAddClientBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddClientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager2 = binding.addClientFragmentViewPager

        // instantiate tab layout
        tabLayout = binding.addClientFragmentTabLayout

        // The pager adapter, which provides the pages to the view pager widget.
        viewPager2.adapter = AddClientFragmentViewPagerAdapter(childFragmentManager, lifecycle)

        // link up tab layout with viewpager2
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.client_account)
                1 -> tab.text = getString(R.string.measurements)
                2 -> tab.text = getString(R.string.delivery_addresses)
            }
        }.attach()
    }

    override fun buttonClicked() {
        viewPager2.currentItem = 1
    }
}
