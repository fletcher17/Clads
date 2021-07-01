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
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.MediaFragmentPhotoName
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentMediaBinding
import com.decagonhq.clads.models.MediaModel
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.FragmentMediaAdapter
import com.decagonhq.clads.utils.helpers.ImageClick
import com.decagonhq.clads.utils.mediaList
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MediaFragment : Fragment(), ImageClick {
    private var _binding: FragmentMediaBinding? = null
    private val binding get() = _binding!!

    private var selectedImage: Uri? = null
    private lateinit var adapter: FragmentMediaAdapter
    private lateinit var photoGalleryModel: MediaModel

    // used to replace startActivity
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts
            .StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            binding.fragmentMediaEmptyImageImageView.visibility = View.GONE
            binding.fragmentMediaNoPhotosYetTextView.visibility = View.GONE
            selectedImage = it.data?.data
            val action = MediaFragmentDirections.actionMediaFragmentToMediaFragmentPhotoName(selectedImage.toString())
            findNavController().navigate(action)
            MediaFragmentPhotoName.DataListener.imageListener.value = false
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

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Bundle>(getString(R.string.image_key))
            ?.observe(viewLifecycleOwner) {
                val imageName = it.getString(getString(R.string.image_name_bundle_key))
                val imageData = it.getString(getString(R.string.image_data_bundle_key))
                val imageDataUri = imageData!!.toUri()
                photoGalleryModel =
                    MediaModel(
                        imageDataUri,
                        imageName!!
                    )
                if (MediaFragmentPhotoName.DataListener.imageListener.value == true) {
                    adapter.add(photoGalleryModel)
                    adapter.notifyDataSetChanged()
                }
            }

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
        if (mediaList.size == 0) {
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
            .setTitle(getString(R.string.fragment_media_permission_title))
            .setMessage(getString(R.string.fragment_media_permission_message))
            .setNegativeButton(getString(R.string.fragment_media_permission_cancel), null)
            .setPositiveButton(getString(R.string.fragment_media_permission_allow)) { dialog, _ ->
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

    override fun onImageClick(imageUri: Uri?, description: String) {
        val action = MediaFragmentDirections.actionMediaFragmentToMediaDisplayPictureFragment(imageUri.toString(), description)
        findNavController().navigate(action)
    }
}
