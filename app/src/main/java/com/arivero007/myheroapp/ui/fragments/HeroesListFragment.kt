package com.arivero007.myheroapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arivero007.myheroapp.adapters.HeroesAdapter
import com.arivero007.myheroapp.databinding.FragmentHeroesListBinding
import com.arivero007.myheroapp.model.HeroeListener
import com.arivero007.myheroapp.network.ApiService
import com.arivero007.myheroapp.network.Resource
import com.arivero007.myheroapp.repositories.HeroesRepository
import com.arivero007.myheroapp.viewmodel.HeroesListViewModel

class HeroesListFragment : BaseFragment<HeroesListViewModel, FragmentHeroesListBinding, HeroesRepository>(), HeroeListener {

    private lateinit var viewAdapter: HeroesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildAdapter()
        observeCharactersData()

        viewModel.getCharacterList()
    }

    private fun observeCharactersData(){
        viewModel.charactersResponse.observe(viewLifecycleOwner, {
            when(it){
                is Resource.Success ->{
                    val result = it.value.data.results
                    viewAdapter.updateData(result)
                }
                is  Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun buildAdapter(){
        viewManager = LinearLayoutManager(requireContext())
        binding.heroesRecyclerview.layoutManager = viewManager
        viewAdapter = HeroesAdapter(this)
        binding.heroesRecyclerview.setHasFixedSize(true)
        binding.heroesRecyclerview.setItemViewCacheSize(20);
        binding.heroesRecyclerview.adapter = viewAdapter
    }

    override fun getViewModel() = HeroesListViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHeroesListBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = HeroesRepository(remoteDataSource.buildApi(ApiService::class.java))

    override fun onHeroClick(id: Int) {

    }

}