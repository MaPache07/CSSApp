package com.uca.app_css.ui.all_projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllProjectsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Opciónes de servicio social"
    }
    val text: LiveData<String> = _text
}