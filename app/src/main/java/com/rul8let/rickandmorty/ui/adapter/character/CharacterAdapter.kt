package com.rul8let.rickandmorty.ui.adapter.character

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rul8let.rickandmorty.data.model.CharacterData

class CharacterAdapter(
    private val selectCharacter: (CharacterData) -> Unit
) : PagingDataAdapter<CharacterData, CharacterItemViewHolder>(characterDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        return CharacterItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
            holder.bind(getItem(position),selectCharacter)
    }

    companion object {
        private val characterDiffUtil = object : DiffUtil.ItemCallback<CharacterData>(){
            override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
                return newItem.id==oldItem.id
            }

            override fun areContentsTheSame(
                oldItem: CharacterData,
                newItem: CharacterData
            ): Boolean {
                return newItem==oldItem
            }

        }
    }
}