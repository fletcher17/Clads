package com.decagonhq.clads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.decagonhq.clads.databinding.FragmentEmailSignUpBinding


class EmailSignUpFragment : Fragment() {
    private var _binding: FragmentEmailSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmailSignUpBinding.inflate(inflater, container, false)
        val accountCategory = resources.getStringArray(R.array.category_list)
        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, accountCategory)
        binding.accountCategoryFilledDropdown.setAdapter(arrayAdapter)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
