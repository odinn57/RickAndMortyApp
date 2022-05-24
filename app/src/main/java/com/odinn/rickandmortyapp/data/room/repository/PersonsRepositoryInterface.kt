package com.odinn.rickandmortyapp.data.room.repository

import androidx.lifecycle.LiveData
import com.odinn.rickandmortyapp.model.Result

interface PersonsRepositoryInterface {
    var allCharacters: LiveData<List<Result>>
    suspend fun insertPerson(person:Result, onSuccess:() -> Unit)
    suspend fun deletePerson(person:Result, onSuccess:() -> Unit)
    suspend fun getPerson(id:Int):Result?
}