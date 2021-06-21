package com.decagonhq.clads.ui.view.profilemanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentProfileSpecialtyTabBinding
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.SpecialtyRecyclerViewAdapter
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileSpecialtyAddSpecialtyCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileSpecialtyCladsTrainedCustomDialogFragment
import com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments.EditProfileSpecialtyDeliveryLeadTimeCustomDialogFragment
import com.decagonhq.clads.ui.viewmodel.EditProfileFragmentViewModel
import com.decagonhq.clads.utils.Interface.IButtonClick

class ProfileSpecialtyTabFragment : Fragment() {

    var _binding: FragmentProfileSpecialtyTabBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: EditProfileFragmentViewModel

    private lateinit var recyclerView: RecyclerView
    interface ButtonClick : IButtonClick

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileSpecialtyTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(EditProfileFragmentViewModel::class.java)

        recyclerView = binding.fragmentProfileSpecialtyRecyclerView
        val recyclerViewAdapter = SpecialtyRecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter

        viewModel.listOfSpecialty.observe(
            viewLifecycleOwner,
            { list ->
                recyclerViewAdapter.setData(list)
            }
        )

        binding.fragmentProfileSpecialtyAddNewSpecialtyTextView.setOnClickListener {

            val dialog = EditProfileSpecialtyAddSpecialtyCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_specialy_fragment_add_specialty_dialog_fragment_tag)
            )
        }

        binding.fragmentProfileSpecialtyAddNewSpecialtyButton.setOnClickListener {

            val dialog = EditProfileSpecialtyAddSpecialtyCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_specialy_fragment_add_specialty_dialog_fragment_tag)
            )
        }

        binding.fragmentProfileSpecialtyCladsTrainedAnswerTextView.setOnClickListener {
            val dialog = EditProfileSpecialtyCladsTrainedCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_specialy_fragment_clads_trained_dialog_fragment_tag)
            )
        }

        viewModel.cladsTrained.observe(
            viewLifecycleOwner,
            {
                cladsTrained ->
                binding.fragmentProfileSpecialtyCladsTrainedAnswerTextView.text = cladsTrained
            }
        )

        binding.fragmentProfileSpecialtyLeadTimeTextView.setOnClickListener {
            val dialog = EditProfileSpecialtyDeliveryLeadTimeCustomDialogFragment()
            dialog.show(
                childFragmentManager,
                getString(R.string.profile_specialy_fragment_delivery_lead_time_dialog_fragment_tag)
            )
        }

        viewModel.deliveryLeadTime.observe(
            viewLifecycleOwner,
            {
                deliveryLeadTime ->
                binding.fragmentProfileSpecialtyLeadTimeTextView.text = deliveryLeadTime
            }
        )

        binding.fragmentProfileSpecialtySaveButton.setOnClickListener {
            (parentFragment as ButtonClick).buttonClicked()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
