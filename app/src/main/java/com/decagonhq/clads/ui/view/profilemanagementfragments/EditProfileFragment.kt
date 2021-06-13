package com.decagonhq.clads.ui.view.profilemanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentEditProfileBinding
import com.decagonhq.clads.ui.adapters.viewpagersadapters.EditProfileFragmentViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class EditProfileFragment : Fragment(), ProfileAccountFragment.ButtonClick, ProfileSpecialtyTabFragment.ButtonClick {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerAdapter: EditProfileFragmentViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = binding.editProfileFragmentViewPager
        tabLayout = binding.editProfileFragmentTabLayout

        viewPagerAdapter = EditProfileFragmentViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = viewPagerAdapter

        tabLayout.addTab(
            tabLayout.newTab().setText(R.string.edit_profile_fragment_tabLayout_account_tab_title)
        )
        tabLayout.addTab(
            tabLayout.newTab().setText(R.string.edit_profile_fragment_tabLayout_specialty_tab_title)
        )
        tabLayout.addTab(
            tabLayout.newTab()
                .setText(R.string.edit_profile_fragment_tabLayout_payment_method_tab_title)
        )
        tabLayout.addTab(
            tabLayout.newTab().setText(R.string.edit_profile_fragment_tabLayout_security_tab_title)
        )

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.editProfileFragmentViewPager.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

    override fun buttonClicked() {
        viewPager.currentItem += 1
    }
}
