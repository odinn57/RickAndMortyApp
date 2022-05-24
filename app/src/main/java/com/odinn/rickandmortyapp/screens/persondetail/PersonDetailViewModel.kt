package com.odinn.rickandmortyapp.screens.persondetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odinn.rickandmortyapp.APP
import com.odinn.rickandmortyapp.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonDetailViewModel:ViewModel() {
    var isFavorite:MutableLiveData<Boolean> = MutableLiveData()
    fun saveCharacter(person:Result, onSuccess:()-> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            APP.mDatabaseRepository.insertPerson(person,onSuccess)
        }

    fun deleteCharacter(person:Result, onSuccess:()-> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            APP.mDatabaseRepository.deletePerson(person,onSuccess)
        }

    fun getPerson(person: Result){
        viewModelScope.launch(Dispatchers.IO) {
            val savedPerson = APP.mDatabaseRepository.getPerson(person.id)
            isFavorite.postValue(savedPerson != null)
        }
    }


}