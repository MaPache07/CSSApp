package com.uca.app_css.ui.my_projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyProjectsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is my_projects Fragment"
    }
    val text: LiveData<String> = _text
}