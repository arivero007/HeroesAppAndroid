package com.arivero007.myheroapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arivero007.myheroapp.databinding.FragmentHeroesListBinding
import com.arivero007.myheroapp.network.ApiService
import com.arivero007.myheroapp.repositories.HeroesRepository
import com.arivero007.myheroapp.viewmodel.HeroesListViewModel

class HeroesListFragment : BaseFragment<HeroesListViewModel, FragmentHeroesListBinding, HeroesRepository>() {

    override fun getViewModel() = HeroesListViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHeroesListBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = HeroesRepository(remoteDataSource.buildApi(ApiService::class.java))

}