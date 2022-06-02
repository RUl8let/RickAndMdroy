package com.rul8let.rickandmorty.ui.adapter.episode

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rul8let.rickandmorty.data.model.EpisodeData

class EpisodeAdapter (private var data : List<EpisodeData>) : RecyclerView.Adapter<EpisodeViewHolder>() {

    fun updateData(it: List<EpisodeData>) {
        data = it
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(data = data[position])
    }

    override fun getItemCount()= data.size
}