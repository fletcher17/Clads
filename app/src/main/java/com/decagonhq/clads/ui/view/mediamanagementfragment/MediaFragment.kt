package com.decagonhq.clads.ui.view.mediamanagementfragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.databinding.FragmentMediaBinding
import com.decagonhq.clads.models.MediaModel
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.FragmentMediaAdapter
import com.decagonhq.clads.utils.Interface.ImageClick
import com.decagonhq.clads.utils.mediaList
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MediaFragment : Fragment(), ImageClick {
    private var _binding: FragmentMediaBinding? = null
    private val binding get() = _binding!!

    private var selectedImage: Uri? = null
    private lateinit var adapter: FragmentMediaAdapter

    // used to replace startActivity
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts
            .StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            binding.fragmentMediaEmptyImageImageView.visibility = View.GONE
            binding.fragmentMediaNoPhotosYetTextView.visibility = View.GONE
            selectedImage = it.data?.data
            selectedImage?.let { it1 -> addMedia(it1) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentMediaBinding.inflate(layoutInflater, container, false)
        adapter = FragmentMediaAdapter(mediaList, this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentMediaPhotoListRecyclerView.adapter = adapter
        displayList()
        adapter.notifyDataSetChanged()

        binding.fragmentMediaAddPhotoFab.setOnClickListener {
            setUpPermission()
        }
    }

    /**
     * [setUpPermission] checks if the user has granted us permission
     * */
    private fun setUpPermission() {
        val permission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                showPermissionDeniedDialog()
            }
        } else {
            makeRequest()
        }
        openImageChooser()
    }

    /**
     * [displayList] returns a list if the list isn't empty
     * */
    private fun displayList() {
        if (mediaList.isEmpty()) {
            binding.fragmentMediaEmptyImageImageView.visibility = View.VISIBLE
            binding.fragmentMediaNoPhotosYetTextView.visibility = View.VISIBLE
        } else {
            binding.fragmentMediaEmptyImageImageView.visibility = View.GONE
            binding.fragmentMediaNoPhotosYetTextView.visibility = View.GONE
        }
    }

    /**
     * [makeRequest] asks the user for permission to access storage
     * */
    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 100
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            100 ->
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    makeRequest()
                }
            else -> {
                openImageChooser()
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Permission to access galary denied")
            .setMessage("Permission denied. Please allow access to gallery for image upload.")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Allow") { dialog, _ ->
                makeRequest()
                dialog.dismiss()
            }
            .show()
    }

    // ImagePicker
    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val types = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, types)
            resultLauncher.launch(it)
        }
    }

    override fun onImageClick(imageUri: Uri?) {
        val action = MediaFragmentDirections.actionMediaFragmentToMediaDisplayPictureFragment(imageUri.toString())
        findNavController().navigate(action)
    }

    override fun editImageDescription(imageDescription: String, position: Int) {
        val editText = EditText(requireContext())
        editText.hint = "Enter Image Description"
        editText.maxLines = 2

        val layout = FrameLayout(requireContext())
        layout.setPaddingRelative(16, 16, 16, 16)

        layout.addView(editText)

        MaterialAlertDialogBuilder(requireContext())
            .setView(layout)
            .setTitle("Enter Image Name")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Save Description") { dialog, _ ->
                mediaList[position].imageDescription = editText.text.toString()
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNeutralButton("Dismiss") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun addMedia(uri: Uri) {
        val media = MediaModel(uri, imageDescription = "Description")
        adapter.add(media)
    }
}
