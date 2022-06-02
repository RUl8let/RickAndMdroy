package com.rul8let.rickandmorty.ui.screen.character.info

import androidx.lifecycle.*
import androidx.paging.LoadState
import com.rul8let.rickandmorty.data.Response
import com.rul8let.rickandmorty.data.model.EpisodeData
import com.rul8let.rickandmorty.data.repository.episode.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val repository: EpisodeRepository
) : ViewModel() {

    private val _episodesLiveData : MutableLiveData<List<EpisodeData>> = MutableLiveData<List<EpisodeData>>()
    val episodesLiveData : LiveData<List<EpisodeData>> = _episodesLiveData

    private val _episodeLoadStat : MutableLiveData<LoadState> = MutableLiveData(LoadState.Loading)
    val episodeLoadStat :LiveData<LoadState> = _episodeLoadStat

    fun getEpisodes(episodes : List<String>){
        viewModelScope.launch {
            repository.getEpisode(episodes).collect {
                when(it){
                    is Response.Error -> _episodeLoadStat.value = LoadState.Error(it.error)
                    Response.Loading -> _episodeLoadStat.value = LoadState.Loading
                    is Response.Success -> {
                        _episodeLoadStat.value= LoadState.NotLoading(true)
                        _episodesLiveData.value = it.data
                    }
                }
            }
        }
    }
}