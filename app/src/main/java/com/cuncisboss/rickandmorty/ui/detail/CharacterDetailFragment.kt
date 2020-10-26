package com.cuncisboss.rickandmorty.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.cuncisboss.rickandmorty.R
import com.cuncisboss.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.cuncisboss.rickandmorty.ui.base.BaseFragment
import com.cuncisboss.rickandmorty.utils.Status
import com.cuncisboss.rickandmorty.utils.gone
import com.cuncisboss.rickandmorty.utils.showLog
import com.cuncisboss.rickandmorty.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>() {

    private val _viewModel by viewModels<CharacterDetailViewModel>()
    private lateinit var binding: FragmentCharacterDetailBinding
    private val charId by lazy {
        arguments?.getInt("charId")
    }

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
    }

    override fun onReadyAction() {
        showLog("CharId: $charId")
        charId?.let {
            _viewModel.getDetail(it)
        }
    }

    override fun onObserveAction() {
        _viewModel.charId.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.gone()
                    it.data?.let { data ->
                        binding.data = data
                    }
                }
                Status.ERROR -> {
                    binding.progressBar.gone()
                    showLog("Character Detail: ${it.message}")
                }
                Status.LOADING -> {
                    binding.progressBar.visible()
                }
            }
        }
    }

    override fun setLayout(): Int = R.layout.fragment_character_detail

    override fun getViewModel(): CharacterDetailViewModel = _viewModel
}