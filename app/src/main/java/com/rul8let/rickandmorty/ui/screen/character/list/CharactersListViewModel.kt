package com.rul8let.rickandmorty.ui.screen.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rul8let.rickandmorty.data.model.CharacterData
import com.rul8let.rickandmorty.data.repository.character.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val repository: CharacterRepository
): ViewModel() {
    val characterFlow : Flow<PagingData<CharacterData>> = repository.getCharacterStream().cachedIn(viewModelScope)
}