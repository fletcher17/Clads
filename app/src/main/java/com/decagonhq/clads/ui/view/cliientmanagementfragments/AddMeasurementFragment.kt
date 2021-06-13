package com.decagonhq.clads.ui.view.cliientmanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.databinding.FragmentAddMeasurementBinding
import com.decagonhq.clads.utils.ClientMeasurementData

class AddMeasurementFragment : Fragment() {


    private var _binding: FragmentAddMeasurementBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddMeasurementBinding.inflate(layoutInflater, container, false)
        val view = binding.root


        //The Add Measurement Button, when clicked Navigate to the Client Measurement Tab Fragment with the Data Entered in the Edit text
        binding.fragmentAddMeasurementButton.setOnClickListener {
            val nameOfMeasurement =
                binding.fragmentAddMeasurementTextInputMeasurementNameEditText.text.toString()
            val nameOfValue =
                binding.fragmentAddMeasurementTextInputMeasurementValueEditText.text.toString()

            if (nameOfMeasurement.isNotEmpty() && nameOfValue.isNotEmpty()) {

                val client = ClientMeasurementData(nameOfMeasurement, nameOfValue)
                val action =
                    AddMeasurementFragmentDirections.actionAddMeasurementFragmentToClientMeasurementTabFragment(
                        client
                    )
                findNavController().navigate(action)
                binding.fragmentAddMeasurementTextInputMeasurementValueEditText.text?.clear()
                binding.fragmentAddMeasurementTextInputMeasurementNameEditText.text?.clear()
            } else {
                Toast.makeText(requireContext(), "measurement is incomplete", Toast.LENGTH_SHORT)
                    .show()
            }

        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
