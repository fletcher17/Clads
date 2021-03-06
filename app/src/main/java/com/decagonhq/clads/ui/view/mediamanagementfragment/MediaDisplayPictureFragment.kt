package com.decagonhq.clads.ui.view.mediamanagementfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.* // ktlint-disable no-wildcard-imports
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.decagonhq.clads.MediaFragmentPhotoName
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentMediaDisplayPictureBinding
import timber.log.Timber

class MediaDisplayPictureFragment : androidx.fragment.app.Fragment() {

    private var _binding: FragmentMediaDisplayPictureBinding? = null
    private val binding get() = _binding!!
    private var imageUri: Uri? = null
    private val args: MediaDisplayPictureFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMediaDisplayPictureBinding.inflate(layoutInflater, container, false)
        imageUri = args.imageUri.toUri()
        val appBarTitleTextView = requireActivity().findViewById<TextView>(R.id.profile_activity_header_app_bar_title_text_view)
        appBarTitleTextView.text = args.description
        Timber.d("title: ${args.description}")
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.media_display_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageDisplayed = binding.fragmentMediaPhotoDisplayImageView
        MediaFragmentPhotoName.DataListener.imageListener.value = false
        Glide.with(this)
            .load(imageUri)
            .into(imageDisplayed)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fragment_media_display_share -> {
                shareImage()
                true
            }
            R.id.fragment_media_display_edit -> {
                editImage()
                true
            }
            R.id.fragment_media_display_delete -> {
                deleteImage()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteImage() {
        Toast.makeText(requireContext(), "Delete Image", Toast.LENGTH_SHORT).show()
    }

    private fun editImage() {
        pickImage.launch("image/*")
    }

    // method to share photo
    private fun shareImage() {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "image/*"
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.fragment_media_share_witn)))
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it.let { uri ->
            binding.fragmentMediaPhotoDisplayImageView.setImageURI(uri)
        }
    }
}
