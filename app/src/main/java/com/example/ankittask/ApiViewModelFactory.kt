package com.example.ankittask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ankittask.repogistory.Repo
import com.example.ankittask.viewmodel.ApiViewModel


class ApiViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApiViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ApiViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}