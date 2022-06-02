package com.rul8let.rickandmorty.ui.adapter.state

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.rul8let.rickandmorty.R
import com.rul8let.rickandmorty.databinding.LoadErrorStateBinding
import com.rul8let.rickandmorty.ui.binding.bind

class LoadStateViewHolder(
    private val binding : LoadErrorStateBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState, retry: () -> Unit) {
        binding.bind(loadState,retry)
    }

    companion object{
        fun create(parent: ViewGroup): LoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.load_error_state,parent,false)
            val binding = LoadErrorStateBinding.bind(view)
            return LoadStateViewHolder(binding)
        }
    }
}