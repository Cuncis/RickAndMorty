package com.cuncisboss.rickandmorty.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

object CharacterDetail {
    @Entity(tableName = "characters")
    data class Response(
        val image: String,
        val gender: String,
        val species: String,
        val created: String,
        val name: String,
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val type: String,
        val url: String,
        val status: String
    ) {
//        @Entity(tableName = "origin")
//        data class Origin(
//            val name: String,
//            val url: String
//        )
//        @Entity(tableName = "location")
//        data class Location(
//            val name: String,
//            val url: String
//        )
    }
}