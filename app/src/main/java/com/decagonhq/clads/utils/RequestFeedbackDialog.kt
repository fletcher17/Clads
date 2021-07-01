package com.decagonhq.clads.utils

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.R

fun Fragment.showLoadingDialog(): Dialog {
    val dialog by lazy {
        Dialog(requireContext(), R.style.Theme_MaterialComponents_Dialog).apply {
            setContentView(R.layout.request_feedback_dialog)
            setCanceledOnTouchOutside(false)
            setTitle("Please wait")
            window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }
    return dialog
}
