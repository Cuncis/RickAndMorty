package com.cuncisboss.rickandmorty.data.remote

import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val apiService: RickAndMorthyApi
) : BaseDataSource() {

    suspend fun getCharacters() = getResult { apiService.getAllCharacterAsync().await() }

    suspend fun getCharacterDetail(id: Int) = getResult { apiService.getCharacterAsync(id).await() }
}