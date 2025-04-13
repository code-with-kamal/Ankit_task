package com.example.ankittask

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.ankittask.api.RetrofitIntance
import com.example.ankittask.errorhandling.ResultState
import com.example.ankittask.repogistory.Repo
import com.example.ankittask.viewmodel.ApiViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var apiViewModel: ApiViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repo(RetrofitIntance.apiService)
        val factory = ApiViewModelFactory(repository)
        apiViewModel = ViewModelProvider(this, factory).get(ApiViewModel::class.java)



        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        apiViewModel.getRepo()
        apiViewModel.getRepo.observe(this) {
            when (it) {
                is ResultState.Error -> {
                    Log.d("testing123", "onCreate: error ${it.exception} ")
                    Log.d("testing123", "onCreate: error ${it.exception} ")
                }

                ResultState.Loading -> {
                    Log.d("testing123", "onCreate: loading ")

                }

                is ResultState.Success -> {

                    Log.d("testing123", "onCreate: success ${it.data} ")

                }
            }
        }
    }
}