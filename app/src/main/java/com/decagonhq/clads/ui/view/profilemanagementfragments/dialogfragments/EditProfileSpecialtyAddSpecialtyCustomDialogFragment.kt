package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.R
import com.decagonhq.clads.data.entity.Specialty
import com.decagonhq.clads.databinding.FragmentProfileSpecialtyAddSpecialtyCustomDialogBinding
import com.decagonhq.clads.ui.viewmodel.EditProfileFragmentViewModel
import com.decagonhq.clads.utils.toast

class EditProfileSpecialtyAddSpecialtyCustomDialogFragment : DialogFragment() {

    private var _binding: FragmentProfileSpecialtyAddSpecialtyCustomDialogBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: EditProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileSpecialtyAddSpecialtyCustomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireParentFragment()).get(EditProfileFragmentViewModel::class.java)

        binding.fragmentProfileSpecialtyAddSpecialtyCustomDialogAddSpecialtyButton.setOnClickListener {
            val specialtyName = binding.fragmentProfileSpecialtyAddSpecialtyCustomDialogAddSpecialtyEditText.editText?.text.toString().trim()

            if (specialtyName != "") {
                viewModel.addToSpecialtyList(Specialty(specialtyName, true))
                dismiss()
            } else {
                toast(getString(R.string.edit_profile_fragment_field_cannot_be_empty_text))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
