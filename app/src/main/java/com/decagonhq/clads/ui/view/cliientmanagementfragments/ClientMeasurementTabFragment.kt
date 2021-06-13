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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.decagonhq.clads.ClientMeasurementAdapter
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentClientMeasurementTabBinding
import com.decagonhq.clads.utils.ClientMeasurementData
import com.decagonhq.clads.utils.Constant.listOfClientData
import com.decagonhq.clads.utils.clicklistener.ClientMeasurementClickListener

class ClientMeasurementTabFragment : Fragment(), ClientMeasurementClickListener {

    private lateinit var clientAdapterMeasurement: ClientMeasurementAdapter
    private val args: ClientMeasurementTabFragmentArgs by navArgs()

    private var _binding: FragmentClientMeasurementTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentClientMeasurementTabBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        /** The float action button navigates to the Add measurement fragment */
        binding.fragmentMeasurementTabFloatActionButton.setOnClickListener {
            findNavController().navigate(R.id.addMeasurementFragment)
        }

        /**client data received from custom args*/
        val clientData = args.clientMeasurement
        if (clientData != null) {
            listOfClientData.add(clientData)
        }
        clientAdapterMeasurement = ClientMeasurementAdapter(listOfClientData, this, this)
        clientAdapterMeasurement.notifyDataSetChanged()


        /**this checks if the list of Client data isn't empty and makes the button and recyclerview visible to display the clients data */
        if (listOfClientData.isNotEmpty()) {
            binding.fragmentMeasurementTabRecyclerview.isVisible = true
            binding.fragmentMeasurementTabButton.isVisible = true
            binding.fragmentAddMeasurementTabTextView.isVisible = false
            binding.fragmentMeasurementTabRecyclerview.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.fragmentMeasurementTabRecyclerview.adapter = clientAdapterMeasurement
        } else {
            binding.fragmentAddMeasurementTabTextView.isVisible = true
        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**item is deleted when clicked*/
    override fun onClickItem(itemName: ClientMeasurementData, position: Int) {
        listOfClientData.removeAt(position)
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
