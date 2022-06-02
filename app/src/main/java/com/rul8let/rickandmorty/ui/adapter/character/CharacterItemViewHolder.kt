package com.rul8let.rickandmorty.ui.adapter.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rul8let.rickandmorty.R
import com.rul8let.rickandmorty.data.model.CharacterData
import com.rul8let.rickandmorty.databinding.CharacterItemBinding

class CharacterItemViewHolder(private val binding: CharacterItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: CharacterData?, selectCharacter: (CharacterData) -> Unit){
        if (data!=null) showData(data,selectCharacter) else loadData()
    }

    private fun loadData(){
        binding.characterName.text = binding.root.context.getText(R.string.load)
    }

    private fun showData(data: CharacterData, selectCharacter: (CharacterData) -> Unit){
        Glide.with(binding.characterAvatar.context)
            .load(data.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.characterAvatar)
        binding.characterName.text = data.name

        binding.characterAvatar.setOnClickListener {
            selectCharacter(data)
        }
    }

    companion object {
        fun create(parent : ViewGroup): CharacterItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.character_item,parent,false)
            val binding = CharacterItemBinding.bind(view)
            return CharacterItemViewHolder(binding)
        }
    }
}