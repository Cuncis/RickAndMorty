package com.cuncisboss.rickandmorty.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cuncisboss.rickandmorty.R
import com.cuncisboss.rickandmorty.data.entities.CharacterDetail
import com.cuncisboss.rickandmorty.databinding.ItemCharacterBinding

class CharactersListAdapter(private val listener: (Int) -> Unit)
    : RecyclerView.Adapter<CharactersListAdapter.ViewHolder>() {

    private var characterList = arrayListOf<CharacterDetail.Response>()

    class ViewHolder(val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_character,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.data = characterList[position]
        holder.itemView.setOnClickListener {
            listener.invoke(characterList[position].id)
        }
    }

    override fun getItemCount(): Int = characterList.size

    fun submitList(list: List<CharacterDetail.Response>) {
        characterList.clear()
        characterList.addAll(list)
        notifyDataSetChanged()
    }
}