package com.decagonhq.clads.ui.view.profilemanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.databinding.FragmentProfileAccountTabBinding
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.*

class ProfileAccountFragment : Fragment() {

    private var _binding: FragmentProfileAccountTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileAccountTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentProfileAccountFirstNameEditText.setOnClickListener {
            val dialog = EditProfileAccountFirstNameCustomDialogFragment()
            dialog.show(childFragmentManager, "firstNameDialog")
        }

        binding.fragmentProfileAccountLastNameEditText.setOnClickListener {
            val dialog = EditProfileAccountLastNameCustomDialogFragment()
            dialog.show(childFragmentManager, "lastNameDialog")
        }

        binding.fragmentProfileAccountOtherNameEditText.setOnClickListener {
            val dialog = EditProfileAccountOtherNameCustomDialogFragment()
            dialog.show(childFragmentManager, "otherNameDialog")
        }

        binding.fragmentProfileAccountShowroomAddressEditText.setOnClickListener {
            val dialog = EditProfileAccountShowroomAddressCustomDialogFragment()
            dialog.show(childFragmentManager, "showroomDialog")
        }

        binding.fragmentProfileAccountWorkshopAddressEditText.setOnClickListener {
            val dialog = EditProfileAccountWorkshopAddressCustomDialogFragment()
            dialog.show(childFragmentManager, "workshopDialog")
        }

        binding.fragmentProfileAccountNumberOfEmployeesEditText.setOnClickListener {
            val dialog = EditProfileAccountNumberOfEmployeesCustomDialogFragment()
            dialog.show(childFragmentManager, "numberOfEmployeesDialog")
        }

        binding.fragmentProfileAccountLegalStatusEditText.setOnClickListener {
            val dialog = EditProfileAccountLegalStatusCustomDialogFragment()
            dialog.show(childFragmentManager, "legalStatusDialog")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
