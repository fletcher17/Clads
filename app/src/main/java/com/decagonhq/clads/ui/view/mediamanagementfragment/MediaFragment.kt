package com.decagonhq.clads.ui.view.mediamanagementfragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentMediaBinding
import com.decagonhq.clads.models.MediaModel
import com.decagonhq.clads.ui.adapters.recyclerviewadapters.FragmentMediaAdapter
import com.decagonhq.clads.utils.Interface.ImageClick
import timber.log.Timber

class MediaFragment : Fragment(), ImageClick {
    private var _binding: FragmentMediaBinding? = null
    private val binding get() = _binding!!
    private lateinit var mediaList: ArrayList<MediaModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentMediaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayMediaList()
        binding.fragmentMediaPhotoListRecyclerView.adapter = FragmentMediaAdapter(mediaList, this)

        binding.fragmentMediaAddPhotoFab.setOnClickListener {
            Toast.makeText(requireContext(), "I am currently available", Toast.LENGTH_SHORT).show()
            setUpPermission()
//            getImageFromGallery()
        }
    }

    private fun displayMediaList() {
        binding.fragmentMediaEmptyImageImageView.visibility = View.GONE
        binding.fragmentMediaNoPhotosYetTextView.visibility = View.GONE
        mediaList = arrayListOf(
            MediaModel(R.drawable.fragment_media_elegant_suit_for_men, "Elegant Suit for men"),
            MediaModel(R.drawable.fragment_media_navy_blue_suit, "Navy blue suit"),
            MediaModel(R.drawable.fragment_media_description, "Description"),
            MediaModel(R.drawable.fragment_media_jenipha_stitches, "Jenipha Stitches"),
            MediaModel(R.drawable.fragment_media_navy_blue_suit, "Navy blue suit"),
        )
    }

    private fun getImageFromGallery() {
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
            Timber.d("setUpPermission: Permission not granted")
            makeRequest()
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
                    Timber.d("Permission has been denied by user")
                }
            else -> Timber.d("Permission has been granted by user")
        }
    }

    override fun onImageClick() {
        findNavController().navigate(R.id.action_mediaFragment_to_mediaDisplayPictureFragment)
    }
}
