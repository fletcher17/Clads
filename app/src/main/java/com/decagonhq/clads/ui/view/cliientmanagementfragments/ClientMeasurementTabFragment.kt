package com.decagonhq.clads.ui.view.cliientmanagementfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentClientMeasurementTabBinding
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.ClientMeasurementAdapter
import com.decagonhq.clads.ui.viewmodel.ClientViewModel
import com.decagonhq.clads.utils.ClientMeasurementData
import com.decagonhq.clads.utils.Constant.listOfClientData
import com.decagonhq.clads.utils.Interface.ClientMeasurementClickListener

class ClientMeasurementTabFragment : Fragment(), ClientMeasurementClickListener {

    private lateinit var clientAdapterMeasurement: ClientMeasurementAdapter
    lateinit var clientMeasurementViewModel: ClientViewModel

    private var _binding: FragmentClientMeasurementTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentClientMeasurementTabBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        clientMeasurementViewModel = ViewModelProvider(requireParentFragment()).get(ClientViewModel::class.java)

        /** The float action button inflates the Add measurement fragment */
        binding.fragmentMeasurementTabFloatActionButton.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext())
            val display = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_add_measurement, null)

            dialog.setView(display)
            val addDialog = dialog.create()
            addDialog.show()

            val measurementName: EditText = display.findViewById(R.id.fragment_add_measurement_textInput_measurement_name_edit_text)
            val measurementValue: EditText = display.findViewById(R.id.fragment_add_measurement_textInput_measurement_value_edit_text)
            val addbutton: Button = display.findViewById(R.id.fragment_add_measurement_button)

            addbutton.setOnClickListener {
                val nameOfMeasurement = measurementName.text.toString()
                val valueOfMeasurement = measurementValue.text.toString()

                val clientMeasurement = ClientMeasurementData(nameOfMeasurement, valueOfMeasurement)

                clientMeasurementViewModel.clientNewMeasurement(clientMeasurement)
                addDialog.dismiss()
            }
        }

        /**client data received from view model*/
        clientMeasurementViewModel.clientMeasurement.observe(
            viewLifecycleOwner,
            Observer {
                listOfClientData.add(it)
                if (listOfClientData.isNotEmpty()) {
                    binding.fragmentMeasurementTabRecyclerview.isVisible = true
                    binding.fragmentMeasurementTabButton.isVisible = true
                    binding.fragmentAddMeasurementTabTextView.isVisible = false
                    clientAdapterMeasurement = ClientMeasurementAdapter(listOfClientData, this, this)
                    binding.fragmentMeasurementTabRecyclerview.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    binding.fragmentMeasurementTabRecyclerview.adapter = clientAdapterMeasurement
                } else if (clientAdapterMeasurement.listOfClientMeasurements.isEmpty()) {
                    binding.fragmentMeasurementTabRecyclerview.isVisible = false
                    binding.fragmentMeasurementTabButton.isVisible = false
                    binding.fragmentAddMeasurementTabTextView.isVisible = true
                }
            }
        )

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**item is deleted when clicked*/
    override fun onClickItem(itemName: ClientMeasurementData, position: Int) {
        clientAdapterMeasurement.listOfClientMeasurements.removeAt(position)
        clientAdapterMeasurement.notifyDataSetChanged()
    }

    /** The item clicked displays the edit dialogue fragment */
    override fun editMeasurement(measurementDetails: ClientMeasurementData, position: Int) {
        var dialog = AlertDialog.Builder(requireContext())
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.fragment_edit_measurement_dialog, null)
        dialog.setView(view)
        val editDialog = dialog.create()
        editDialog.show()

        val measurementName: EditText =
            view.findViewById(R.id.dialog_fragment_edit_measurement_textInput_measurement_name_edit_text)
        val measurementValue: EditText =
            view.findViewById(R.id.dialog_fragment_edit_measurement_textInput_measurement_value_edit_text)
        val saveButton: Button =
            view.findViewById(R.id.dialog_fragment_edit_measurement_save_button)

        saveButton.setOnClickListener {
            measurementDetails.nameOfMeasurement = measurementName.text.toString()
            measurementDetails.valueOfMeasurement = measurementValue.text.toString()
            clientAdapterMeasurement.notifyDataSetChanged()
            editDialog.dismiss()
        }
    }
}
