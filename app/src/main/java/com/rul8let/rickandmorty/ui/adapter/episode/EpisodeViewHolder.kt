package com.rul8let.rickandmorty.ui.adapter.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rul8let.rickandmorty.R
import com.rul8let.rickandmorty.data.model.EpisodeData
import com.rul8let.rickandmorty.databinding.EpisodeItemBinding

class EpisodeViewHolder(private val binding : EpisodeItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data : EpisodeData) {
        val episode = binding.root.context.getString(R.string.episode,data.episode)
        binding.episodeNumber.text = episode
        binding.nameEpisode.text = data.name
        binding.dataCreateEpisode.text = data.air_date
    }

    companion object {
        fun create(parent : ViewGroup): EpisodeViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.episode_item,parent,false)
            val binding = EpisodeItemBinding.bind(view)
            return EpisodeViewHolder(binding)
        }
    }
}