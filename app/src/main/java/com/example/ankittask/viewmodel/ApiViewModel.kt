package com.example.ankittask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ankittask.errorhandling.ResultState
import com.example.ankittask.model.DataModel
import com.example.ankittask.repogistory.Repo
import kotlinx.coroutines.launch

class ApiViewModel(val repo: Repo) : ViewModel() {
    private var _getRepo: MutableLiveData<ResultState<DataModel>> = MutableLiveData()
    val getRepo: LiveData<ResultState<DataModel>> = _getRepo
    fun getRepo() {
        _getRepo.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val response = repo.getRepo()
                if (response.isSuccessful && response.body() != null) {
                    _getRepo.postValue(ResultState.Success(response.body()!!))
                } else {
                    _getRepo.postValue(ResultState.Error(response.errorBody().toString()))
                }
            } catch (e: Exception) {
                _getRepo.postValue(ResultState.Error(e.message.toString()))

            }


        }
    }
}