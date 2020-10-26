package com.cuncisboss.rickandmorty.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cuncisboss.rickandmorty.R
import com.cuncisboss.rickandmorty.databinding.FragmentCharacterListBinding
import com.cuncisboss.rickandmorty.ui.base.BaseFragment
import com.cuncisboss.rickandmorty.utils.Status
import com.cuncisboss.rickandmorty.utils.gone
import com.cuncisboss.rickandmorty.utils.showLog
import com.cuncisboss.rickandmorty.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : BaseFragment<FragmentCharacterListBinding, CharacterListViewModel>(), CharacterListNavigator {

    private val _viewModel by viewModels<CharacterListViewModel>()
    private lateinit var binding: FragmentCharacterListBinding

    override fun onInitialization() {
        super.onInitialization()
        binding = getViewDataBinding()
        _viewModel.navigator = this
        binding.vm = _viewModel
    }

    override fun onObserveAction() {
        _viewModel.characters.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressbar.gone()
                    it.data?.let { list ->
                        _viewModel.adapter.submitList(list)
                    }
                }
                Status.ERROR -> {
                    binding.progressbar.gone()
                    showLog("CharacterList: ${it.message}")
                }
                Status.LOADING -> {
                    binding.progressbar.visible()
                }
            }
        }
    }

    override fun setLayout(): Int = R.layout.fragment_character_list

    override fun getViewModel() = _viewModel

    override fun goToDetail(id: Int) {
        findNavController().navigate(
                R.id.action_characterListFragment_to_characterDetailFragment,
                bundleOf("charId" to id)
        )
    }
}