package com.example.ankittask.repogistory

import com.example.ankittask.api.ApiService
import com.example.ankittask.model.DataModel
import retrofit2.Response

class Repo(private val apiService: ApiService) {

    suspend fun getRepo(): Response<DataModel> {
        return apiService.getrepo()
    }
}