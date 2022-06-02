package com.rul8let.rickandmorty.ui.screen.character.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rul8let.rickandmorty.R
import com.rul8let.rickandmorty.data.model.CharacterData
import com.rul8let.rickandmorty.databinding.CharacterInfoBinding
import com.rul8let.rickandmorty.ui.adapter.episode.EpisodeAdapter
import com.rul8let.rickandmorty.ui.binding.bind
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {

    private var _binding : CharacterInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CharacterInfoViewModel by viewModels()

    private val args: CharacterInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CharacterInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun CharacterInfoBinding.bindInfo(character: CharacterData) {
        val typeText = character.type.ifEmpty { getString(R.string.unknown) }

        name.text = character.name
        status.text = getString(R.string.status,character.status)
        species.text = getString(R.string.species,character.species)
        type.text = getString(R.string.type,typeText)
        gender.text = getString(R.string.gender,character.gender)

        Glide.with(avatarImage.context)
            .load(character.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(avatarImage)
    }

    private fun CharacterInfoBinding.bindEpisode() {
        viewModel.getEpisodes(args.character.episode)

        viewModel.episodeLoadStat.observe(viewLifecycleOwner){ loadState->
            if (loadState is LoadState.NotLoading) {
                loadErrorLayout.root.isVisible = false
            } else {
                loadErrorLayout.root.isVisible = true
                loadErrorLayout.bind(loadState) {
                    viewModel.getEpisodes(args.character.episode)
                }
            }
        }

        binding.binAdapter()
    }

    private fun CharacterInfoBinding.binAdapter() {
        val adapter = EpisodeAdapter(emptyList())

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        episodeList.addItemDecoration(itemDecoration)
        episodeList.adapter = adapter

        viewModel.episodesLiveData.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindInfo(args.character)
        binding.bindEpisode()
    }
}