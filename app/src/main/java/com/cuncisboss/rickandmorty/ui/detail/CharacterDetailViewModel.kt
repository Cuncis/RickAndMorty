package com.cuncisboss.rickandmorty.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cuncisboss.rickandmorty.data.entities.CharacterDetail
import com.cuncisboss.rickandmorty.data.repository.CharacterRepository
import com.cuncisboss.rickandmorty.ui.base.BaseViewModel
import com.cuncisboss.rickandmorty.utils.Resource

class CharacterDetailViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : BaseViewModel<CharacterDetailNavigator>() {

    private var _charId = MutableLiveData<Resource<CharacterDetail.Response>>()
    val charId: LiveData<Resource<CharacterDetail.Response>>
        get() = _charId

    fun getDetail(id: Int) {
        _charId = repository.getCharacter(id) as MutableLiveData<Resource<CharacterDetail.Response>>
    }
}