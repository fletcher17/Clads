package com.decagonhq.clads.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.decagonhq.clads.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientManagementViewModel @Inject constructor (private val repository: Repository) : ViewModel()
