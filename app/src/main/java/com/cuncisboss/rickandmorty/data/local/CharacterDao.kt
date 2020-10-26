package com.cuncisboss.rickandmorty.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuncisboss.rickandmorty.data.entities.Character
import com.cuncisboss.rickandmorty.data.entities.CharacterDetail

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): LiveData<List<CharacterDetail.Response>>

    @Query("SELECT * FROM characters WHERE id=:id")
    fun getCharacterDetail(id: Int): LiveData<CharacterDetail.Response>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterDetail.Response>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(detail: CharacterDetail.Response)
}