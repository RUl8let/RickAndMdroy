package com.rul8let.rickandmorty.ui.binding

import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.rul8let.rickandmorty.databinding.LoadErrorStateBinding

fun LoadErrorStateBinding.bind(loadState: LoadState, retry: () -> Unit){
    var error = false
    if (loadState is LoadState.Error){
        error = true
        errorText.text = loadState.error.message
    }
    errorText.isVisible = error
    retryButton.isVisible = error
    progressBar.isVisible = !error

    retryButton.setOnClickListener { retry() }
}