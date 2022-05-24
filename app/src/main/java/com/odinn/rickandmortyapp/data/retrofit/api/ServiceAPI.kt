package com.odinn.rickandmortyapp.data.retrofit.api

import com.odinn.rickandmortyapp.ALL_CHARACTERS
import com.odinn.rickandmortyapp.model.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {
    @GET(ALL_CHARACTERS)
    fun getAllCharacters(@Query("page") page:Int = 1): Call<Characters>
}