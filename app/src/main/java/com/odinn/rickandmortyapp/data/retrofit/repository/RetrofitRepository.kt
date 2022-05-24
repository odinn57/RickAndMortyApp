package com.odinn.rickandmortyapp.data.retrofit.repository

import android.util.Log
import android.widget.Toast
import com.odinn.rickandmortyapp.data.retrofit.api.RetrofitInstance
import com.odinn.rickandmortyapp.model.Characters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class RetrofitRepository {
    suspend fun getAllCharacters(page:Int = 1): Characters?{
        val response = RetrofitInstance.api.getAllCharacters(page).awaitResponse()
        return if (response.isSuccessful){
            response.body()
        }else{
            Log.e("Error Retrofit","Error in Retrofit Repository: method getAllCharacters()")
            null
        }

    }
}