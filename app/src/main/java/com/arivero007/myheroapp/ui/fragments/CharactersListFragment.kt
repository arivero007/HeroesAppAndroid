package com.arivero007.myheroapp.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arivero007.myheroapp.R
import com.arivero007.myheroapp.adapters.CharactersAdapter
import com.arivero007.myheroapp.databinding.FragmentCharactersListBinding
import com.arivero007.myheroapp.model.CharacterListener
import com.arivero007.myheroapp.data.network.ApiService
import com.arivero007.myheroapp.data.network.Resource
import com.arivero007.myheroapp.data.repositories.CharactersRepository
import com.arivero007.myheroapp.viewmodel.CharactersListViewModel

class HeroesListFragment : BaseFragment<CharactersListViewModel, FragmentCharactersListBinding, CharactersRepository>(), CharacterListener {

    private lateinit var viewAdapter: CharactersAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initSearchItem()
        buildListeners()
        buildAdapter()
        observeCharactersData()

        viewModel.getCharacterList()
    }

    private fun observeCharactersData(){
        viewModel.charactersResponse.observe(viewLifecycleOwner, {
            when(it){
                is Resource.Success -> {
                    val result = it.value.data.results
                    viewAdapter.updateData(result)
                }
                is  Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun buildListeners(){
        binding.toolbar.setOnMenuItemClickListener{ toolbarListener(it)}
    }

    private fun initSearchItem(){
        val search = binding.searchView

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewAdapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun buildAdapter(){
        viewManager = LinearLayoutManager(requireContext())
        binding.heroesRecyclerview.layoutManager = viewManager
        viewAdapter = CharactersAdapter(this)
        binding.heroesRecyclerview.setHasFixedSize(true)
        binding.heroesRecyclerview.setItemViewCacheSize(20);
        binding.heroesRecyclerview.adapter = viewAdapter
    }

    override fun getViewModel() = CharactersListViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCharactersListBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = CharactersRepository(remoteDataSource.buildApi(ApiService::class.java), userPreferences)

    override fun onHeroClick(id: Int) {
        viewModel.saveCharacterId(id)
        findNavController().navigate(
            HeroesListFragmentDirections.actionHeroesListFragmentToHeroeFragment()
        )
    }

    private fun toolbarListener(it: MenuItem): Boolean {
        return when (it.itemId) {
            R.id.refresh_item -> {
                viewModel.getCharacterList()
                true
            }
            else -> false
        }
    }

}