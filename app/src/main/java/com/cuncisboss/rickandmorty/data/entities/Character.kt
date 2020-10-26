package com.cuncisboss.rickandmorty.data.entities

object Character {
    data class Response(
        val info: Info,
        val results: List<CharacterDetail.Response>
    ) {
        data class Info(
            val count: Int,
            val pages: Int,
            val next: String? = null,
            val prev: String? = null
        )
    }
}