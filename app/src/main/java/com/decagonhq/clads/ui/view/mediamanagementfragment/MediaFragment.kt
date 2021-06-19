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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.databinding.FragmentMediaBinding
import com.decagonhq.clads.models.MediaModel
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.FragmentMediaAdapter
import com.decagonhq.clads.utils.Interface.ImageClick
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import timber.log.Timber

class MediaFragment : Fragment(), ImageClick {
    private var _binding: FragmentMediaBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FragmentMediaAdapter

    private var selectedImage: Uri? = null

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
            Timber.d("$selectedImage")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentMediaBinding.inflate(layoutInflater, container, false)
        adapter = FragmentMediaAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentMediaPhotoListRecyclerView.adapter = adapter
//        selectedImage?.let { it1 -> addMedia(it1) }

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
                Timber.d("setUpPermission: Permission not granted")
                showPermissionDeniedDialog()
            }
        } else {
            makeRequest()
        }
        openImageChooser()
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
                    Timber.d("Permission has been denied by user")
                }
            else -> {
                openImageChooser()
                Timber.d("Permission has been granted by user")
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

    override fun onImageClick() {
        val action = MediaFragmentDirections.actionMediaFragmentToMediaDisplayPictureFragment(
            MediaModel(selectedImage)
        )
        findNavController().navigate(action)
    }

    private fun addMedia(uri: Uri) {
        val media = MediaModel(uri)
        adapter.add(media)
    }
}
