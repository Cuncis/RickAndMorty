package com.cuncisboss.rickandmorty.data.repository

import com.cuncisboss.rickandmorty.data.local.CharacterDao
import com.cuncisboss.rickandmorty.data.remote.CharacterRemoteDataSource
import com.cuncisboss.rickandmorty.utils.performGetOperation
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterDao
) {
    fun getCharacter(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacterDetail(id) },
        networkCall = { remoteDataSource.getCharacterDetail(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getCharacters() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )
}