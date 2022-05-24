package com.odinn.rickandmortyapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.odinn.rickandmortyapp.data.room.dao.PersonsDAO
import com.odinn.rickandmortyapp.model.Result

@Database(entities = [Result::class], version = 2)
abstract class PersonsRoomDatabase: RoomDatabase() {
    abstract fun getPersonsDAO(): PersonsDAO

    companion object{
        private var database: PersonsRoomDatabase ?= null

        fun getInstance(context: Context): PersonsRoomDatabase{
            return if (database == null){
                database = Room.databaseBuilder(context,PersonsRoomDatabase::class.java,"characters_db").build()
                database as PersonsRoomDatabase
            }else{
                database as PersonsRoomDatabase
            }
        }
    }
}