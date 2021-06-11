package com.decagonhq.clads.ui.view.profilemanagementfragments.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.databinding.FragmentProfileSpecialtyAddSpecialtyCustomDialogBinding
import com.decagonhq.clads.models.Specialty
import com.decagonhq.clads.viewmodel.EditProfileFragmentViewModel

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
            val specialtyName = binding.fragmentProfileSpecialtyAddSpecialtyCustomDialogAddSpecialtyEditText.editText?.text.toString()
            viewModel.addToSpecialtyList(Specialty(specialtyName, true))
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
