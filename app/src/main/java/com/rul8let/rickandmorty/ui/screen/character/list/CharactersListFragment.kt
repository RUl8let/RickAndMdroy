package com.rul8let.rickandmorty.ui.screen.character.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.rul8let.rickandmorty.data.model.CharacterData
import com.rul8let.rickandmorty.databinding.CharactersBinding
import com.rul8let.rickandmorty.ui.adapter.character.CharacterAdapter
import com.rul8let.rickandmorty.ui.adapter.state.LoadStateAdapter
import com.rul8let.rickandmorty.ui.binding.bind
import com.rul8let.rickandmorty.ui.util.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    private var _binding : CharactersBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CharactersListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CharactersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindAdapter()
    }

    private fun CharactersBinding.bindAdapter(){
        val adapter = CharacterAdapter(selectCharacter)


        characterList.layoutManager = GridLayoutManager(context,2)
        characterList.adapter = adapter.withLoadStateFooter(LoadStateAdapter{adapter.retry()})

        lifecycleScope.launch {
            viewModel.characterFlow.collectLatest (adapter::submitData)
        }

        adapter.addLoadStateListener {
            val loadState = it.refresh
            if (loadState is LoadState.NotLoading||adapter.itemCount>0) {
                loadErrorLayout.root.isVisible = false
            } else {
                loadErrorLayout.root.isVisible = true
                loadErrorLayout.bind(loadState) { adapter.retry() }
            }

        }
    }

    private val selectCharacter = fun (data:CharacterData){
        val action =
            CharactersListFragmentDirections.actionCharactersListFragmentToCharacterInfoFragment(
                data
            )
        findNavController().safeNavigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}