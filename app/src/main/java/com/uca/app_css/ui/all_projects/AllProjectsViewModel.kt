package com.uca.app_css.ui.all_projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllProjectsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is all_projects Fragment"
    }
    val text: LiveData<String> = _text
}