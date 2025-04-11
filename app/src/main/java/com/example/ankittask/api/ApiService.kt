package com.example.ankittask.api

import com.example.ankittask.model.DataModel
import retrofit2.http.GET

interface ApiService {
    @GET("/users/google/repos")
    suspend  fun getrepo():retrofit2.Response<DataModel>
}