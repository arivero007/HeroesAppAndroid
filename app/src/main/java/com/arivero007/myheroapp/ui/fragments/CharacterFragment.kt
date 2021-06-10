package com.arivero007.myheroapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arivero007.myheroapp.data.network.ApiService
import com.arivero007.myheroapp.data.network.Resource
import com.arivero007.myheroapp.data.repositories.CharacterRepository
import com.arivero007.myheroapp.databinding.FragmentCharacterBinding
import com.arivero007.myheroapp.model.Character
import com.arivero007.myheroapp.viewmodel.CharacterViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.runBlocking

class CharacterFragment : BaseFragment<CharacterViewModel, FragmentCharacterBinding, CharacterRepository>() {

    private val TAG = "CharacterFragment: "

    override fun getViewModel() = CharacterViewModel::class.java

    override fun getFragmentRepository() = CharacterRepository(remoteDataSource.buildApi(ApiService::class.java), userPreferences)

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCharacterBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: Int
        runBlocking {
            id = viewModel.getCharacterId()
            Log.v(TAG, id.toString())
        }

        onObserveCharacter()
        viewModel.getCharacterInfo(id)
    }

    private fun onObserveCharacter(){
        viewModel.characterResponse.observe(viewLifecycleOwner, {
            when(it){
                is Resource.Success -> {
                    setHeroData(it.value.data.results.first())
                }
                is Resource.Failure -> {
                    Toast(requireContext(), )
                }
            }
        })
    }

    private fun setHeroData(hero: Character){
        Log.d(TAG, hero.toString())
        binding.heroeName.text = hero.name
        val imgUrl = hero.thumbnail.path + "." + hero.thumbnail.extension
        Picasso.get().setIndicatorsEnabled(true)
        Picasso.get().load(imgUrl).into(binding.heroeImage)
        binding.heroeDescription.text = hero.description
        binding.heroeModified.text = hero.modified
    }

}