package com.cuncisboss.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cuncisboss.rickandmorty.data.entities.CharacterDetail

@Database(entities = [CharacterDetail.Response::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}