package com.odinn.rickandmortyapp.data.room.repository

import androidx.lifecycle.LiveData
import com.odinn.rickandmortyapp.data.room.dao.PersonsDAO
import com.odinn.rickandmortyapp.model.Result

class PersonsRepository(private val personsDAO: PersonsDAO): PersonsRepositoryInterface {
    override var allCharacters: LiveData<List<Result>> = personsDAO.getAllCharacters()

    override suspend fun insertPerson(person: Result, onSuccess: () -> Unit) {
        personsDAO.insert(person)
        onSuccess()
    }

    override suspend fun deletePerson(person: Result, onSuccess: () -> Unit) {
        personsDAO.delete(person)
        onSuccess()
    }

    override suspend fun getPerson(id: Int):Result? = personsDAO.getCharacter(id)



}