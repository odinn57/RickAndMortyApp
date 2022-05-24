package com.odinn.rickandmortyapp.screens.personlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odinn.rickandmortyapp.data.retrofit.repository.RetrofitRepository
import com.odinn.rickandmortyapp.model.Characters
import kotlinx.coroutines.launch


class PersonListViewModel:ViewModel() {
    private val repository = RetrofitRepository()
    val mCharacter:MutableLiveData<Characters> = MutableLiveData()

    fun getAllCharacters(page:Int = 1){
        viewModelScope.launch {
            val characters = repository.getAllCharacters(page)
            mCharacter.value = characters
        }
    }
}