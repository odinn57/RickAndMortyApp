package com.odinn.rickandmortyapp.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.odinn.rickandmortyapp.model.Result

@Dao
interface PersonsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person:Result)

    @Delete
    suspend fun delete(person:Result)

    @Query("SELECT * FROM person_table")
    fun getAllCharacters(): LiveData<List<Result>>

    @Query("SELECT * FROM person_table WHERE id = :id")
    fun getCharacter(id: Int): Result?

}