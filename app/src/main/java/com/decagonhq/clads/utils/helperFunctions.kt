package com.decagonhq.clads.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.decagonhq.clads.resource.Resource
import com.decagonhq.clads.ui.view.authenticationfragments.LoginFragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.toast(message: String) {
    Toast.makeText(this.requireActivity(), message, Toast.LENGTH_SHORT).show()
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    when {
        failure.isNetworkError -> requireView().snackbar("Please check your internet connection", retry)
        failure.errorCode == 401 -> {
            if (this is LoginFragment) {
                requireView().snackbar("You have entered incorrect email or password")
            }
        }
        failure.errorCode == 400 -> {
            requireView().snackbar("Invalid Username and Password")
        }
        else -> {
            val error = failure.errorCode.toString()
            requireView().snackbar(error)
        }
    }
}
