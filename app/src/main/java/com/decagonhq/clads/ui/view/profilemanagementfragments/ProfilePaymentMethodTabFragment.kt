package com.decagonhq.clads.ui.view.profilemanagementfragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.* // ktlint-disable no-wildcard-imports
import androidx.fragment.app.Fragment
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.EditProfilePaymentMethodAddPaymentDialogBinding
import com.decagonhq.clads.databinding.EditProfilePaymentOptionsDialogBinding
import com.decagonhq.clads.databinding.EditProfilePaymentTermsDialogBinding
import com.decagonhq.clads.databinding.FragmentProfilePaymentMethodTabBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

private var selectedPaymentOption = ""

class ProfilePaymentMethodTabFragment : Fragment() {

    private lateinit var binding: FragmentProfilePaymentMethodTabBinding
    private lateinit var paymentTerms: TextView
    private lateinit var paymentOptions: TextView
    private lateinit var addPaymentTerms: FloatingActionButton
    private lateinit var saveChanges: Button
    private lateinit var addNewPaymentDialogBinding: EditProfilePaymentMethodAddPaymentDialogBinding
    private lateinit var paymentOptionsDialogBinding: EditProfilePaymentOptionsDialogBinding
    private lateinit var paymentTermsDialogBinding: EditProfilePaymentTermsDialogBinding
    private lateinit var submitAddPaymentTermsBtn: Button
    private lateinit var inputPaymentTermsEditText: EditText
    private lateinit var nairaCheckBox: CheckBox
    private lateinit var usdCheckBox: CheckBox
    private lateinit var cashCheckBox: CheckBox
    private lateinit var vCashCheckBox: CheckBox
    private lateinit var payoneerCheckBox: CheckBox
    private lateinit var selectedPaymentOptions: MutableSet<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilePaymentMethodTabBinding.inflate(
            layoutInflater,
            container,
            false
        )

        // Initializing binding variables for the dialog xmls
        addNewPaymentDialogBinding =
            EditProfilePaymentMethodAddPaymentDialogBinding.inflate(layoutInflater)

        // creating reference to views
        paymentTerms =
            binding.fragmentProfilePagePaymentMethodTabScreenPaymentTermsUserChoiceModalTextView
        paymentOptions =
            binding.fragmentProfilePaymentMethodTabScreenPaymentOptionsUserChoiceModalTextView
        addPaymentTerms = binding.fragmentProfileAddNewPaymentTermsFloatingActionButton
        saveChanges = binding.fragmentProfilePaymentMethodTabScreenSaveChangesBtn

        // Initializing the variable for storing payment options
        selectedPaymentOptions = mutableSetOf()
        selectedPaymentOptions.add("Bank deposit (Naira)")
        paymentOptions.text = selectedPaymentOptions.joinToString(
            separator = "\n "
        )

        val allPaymentTermsInDataBase = arrayListOf(
            "100% Deposit",
            "50% Deposit and 50% balance on delivery",
            "0% Deposit and 100% balance on delivery"
        )

        // creating alert dialogs
        val addNewPaymentDialog = createDialog(addNewPaymentDialogBinding.root).create()

        // getting reference to the views inside the add new payment dialog
        submitAddPaymentTermsBtn = addNewPaymentDialogBinding.addPaymentTermsBtn
        inputPaymentTermsEditText = addNewPaymentDialogBinding.addPaymentTermsTextView

        // setting onclick listener to the addPaymentTerms(Floating Action Button)
        addPaymentTerms.setOnClickListener() {
            addNewPaymentDialog.show()

            // getting data from the Add new payment terms dialog
            submitAddPaymentTermsBtn.setOnClickListener() {
                if (inputPaymentTermsEditText.text.isNotEmpty()) {
                    val newPaymentTerms = inputPaymentTermsEditText.text.toString()

                    inputPaymentTermsEditText.text.clear()

                    addNewPaymentDialog.dismiss()

                    // Adding the created payment term to the payment term resources_view_individual_video_design_layout_how_to_sew_text_view
                    paymentTerms.text = newPaymentTerms
                } else {
                    inputPaymentTermsEditText.error = "Field must not be empty"
                }
            }
        }

        // Setting onClick listener to the payment options resources_view_individual_video_design_layout_how_to_sew_text_view
        paymentOptions.setOnClickListener() {
            // Intializing the binding variable
            paymentOptionsDialogBinding =
                EditProfilePaymentOptionsDialogBinding.inflate(layoutInflater)

            // initailizing the check boxes
            nairaCheckBox =
                paymentOptionsDialogBinding.fragmentProfilePaymentOptionsBankDepositNaira
            usdCheckBox =
                paymentOptionsDialogBinding.fragmentProfilePaymentOptionsBankDepositForeignCurrencies
            cashCheckBox = paymentOptionsDialogBinding.fragmentProfilePaymentOptionsCash
            vCashCheckBox = paymentOptionsDialogBinding.fragmentProfilePaymentOptionsVCash
            payoneerCheckBox = paymentOptionsDialogBinding.fragmentProfilePaymentOptionsPayoneer

            // Building the Dialog
            val paymentOptionsDialog = createDialog(paymentOptionsDialogBinding.root)

            // Setting the positive button for the payment options dialog builder
            paymentOptionsDialog.setPositiveButton(
                R.string.all_ok
            ) { _: DialogInterface, _: Int ->
                addSelectedCheckBox(nairaCheckBox)
                addSelectedCheckBox(usdCheckBox)
                addSelectedCheckBox(cashCheckBox)
                addSelectedCheckBox(vCashCheckBox)
                addSelectedCheckBox(payoneerCheckBox)

                if (selectedPaymentOptions.isEmpty()) {
                    selectedPaymentOptions.add("Bank deposit (Naira)")
                }

                // updating the payment options TextView
                paymentOptions.text = selectedPaymentOptions.joinToString(
                    separator = "\n  "
                )
            }

            // Setting the Negative button for the payment options dialog builder
            paymentOptionsDialog.setNegativeButton(
                R.string.all_cancel
            ) { _: DialogInterface, _: Int ->
            }

            paymentOptionsDialog.create()
            paymentOptionsDialog.show()
        }

        // setting onClick Listener to the payment terms resources_view_individual_video_design_layout_how_to_sew_text_view
        paymentTerms.setOnClickListener {
            // Initializing the binding variable
            paymentTermsDialogBinding = EditProfilePaymentTermsDialogBinding.inflate(layoutInflater)

            // Getting reference to the RecyclerView RadioGroup
            val paymentTermsRadioGroup =
                paymentTermsDialogBinding.fragmentProfilePaymentMethodRadioGroup

            // populating the RadioGroup dynamically
            for (i in allPaymentTermsInDataBase) {
                val radioButton = RadioButton(requireContext())
                radioButton.text = i
                radioButton.setTextColor(resources.getColor(R.color.menu_text_color))
                radioButton.textSize = 20.0f
                radioButton.buttonTintList = resources.getColorStateList(R.color.deep_sky_blue)
                radioButton.setPadding(16, 5, 5, 5)
                radioButton.setOnClickListener() {
                    selectedPaymentOption = i
                }

                // attach it to the radio group
                paymentTermsRadioGroup.addView(radioButton)
            }

            // building the dialog
            val paymentTermsDialog = AlertDialog.Builder(requireContext())
            paymentTermsDialog.setView(paymentTermsDialogBinding.root)

            paymentTermsDialog.setPositiveButton(
                R.string.all_ok
            ) { _: DialogInterface, _: Int ->
                paymentTerms.text = selectedPaymentOption
            }

            paymentTermsDialog.setNegativeButton(
                R.string.all_cancel
            ) { _: DialogInterface, _: Int ->
            }

            paymentTermsDialog.create().show()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun createDialog(view: View): AlertDialog.Builder {
        // creating alert dialog builder
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setView(view)
        return dialogBuilder
    }

    private fun addSelectedCheckBox(checkBox: CheckBox) {
        if (checkBox.isChecked) {
            selectedPaymentOptions.add(checkBox.text.toString())
        } else {
            selectedPaymentOptions.remove(checkBox.text.toString())
        }
    }
}
