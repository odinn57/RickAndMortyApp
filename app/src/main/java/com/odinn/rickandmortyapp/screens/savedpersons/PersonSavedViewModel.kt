package com.odinn.rickandmortyapp.screens.savedpersons

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.odinn.rickandmortyapp.APP
import com.odinn.rickandmortyapp.model.Result

class PersonSavedViewModel:ViewModel() {
    fun getAllSavedPersons():LiveData<List<Result>>{
        return APP.mDatabaseRepository.allCharacters
    }
}