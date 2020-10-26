package com.cuncisboss.rickandmorty.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.cuncisboss.rickandmorty.data.repository.CharacterRepository
import com.cuncisboss.rickandmorty.ui.base.BaseViewModel

class CharacterListViewModel @ViewModelInject constructor(
    repository: CharacterRepository
) : BaseViewModel<CharacterListNavigator>() {

    val adapter = CharactersListAdapter {
        navigator?.goToDetail(it)
    }

    val characters = repository.getCharacters()
}