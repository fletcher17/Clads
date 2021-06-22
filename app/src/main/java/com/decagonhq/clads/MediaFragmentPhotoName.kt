package com.decagonhq.clads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.decagonhq.clads.databinding.FragmentMediaPhotoNameBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MediaFragmentPhotoName : Fragment() {

    private var _binding: FragmentMediaPhotoNameBinding? = null
    private val binding get() = _binding!!

    private val args: MediaFragmentPhotoNameArgs by navArgs()
    private lateinit var sendFab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMediaPhotoNameBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendFab = binding.mediaFragmentPhotoNameSendButton
        val photoGallery = binding.photoGalleryImage
        val photoData = args.imageDetails

        /*load the image sent from media fragment*/
        Glide.with(this)
            .load(photoData)
            .into(photoGallery)

        /*click the send fab to send photo and name of photo back to media fragment*/
        sendFab.setOnClickListener {
            val imageName = binding.mediaFragmentPhotoNameEditText.text.toString()
            val imageData = args.imageDetails
            DataListener.imageListener.value = true

            val bundle = bundleOf(
                getString(R.string.image_name_bundle_key) to imageName,
                getString(R.string.image_data_bundle_key) to imageData
            )

            if (imageName.isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.fragment_media_enter_name), Toast.LENGTH_LONG).show()
            } else {
                findNavController().previousBackStackEntry?.savedStateHandle?.set(getString(R.string.image_key), bundle)
                findNavController().popBackStack()
            }
        }
    }

    object DataListener {
        var imageListener = MutableLiveData<Boolean>()
        init {
            imageListener.value = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
