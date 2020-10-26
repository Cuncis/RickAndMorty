package com.cuncisboss.rickandmorty.data.remote

import com.cuncisboss.rickandmorty.data.entities.Character
import com.cuncisboss.rickandmorty.data.entities.CharacterDetail
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMorthyApi {

    @GET("character")
    fun getAllCharacterAsync(): Deferred<Response<Character.Response>>

    @GET("character/{id}")
    fun getCharacterAsync(
        @Path("id") id: Int
    ): Deferred<Response<CharacterDetail.Response>>
}