package com.arivero007.myheroapp.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.arivero007.myheroapp.databinding.FragmentHeroeBinding
import com.arivero007.myheroapp.repositories.HeroeRepository
import com.arivero007.myheroapp.viewmodel.HeroeViewModel

class HeroeFragment : BaseFragment<HeroeViewModel, FragmentHeroeBinding, HeroeRepository>() {

    override fun getViewModel(): Class<HeroeViewModel> {
        TODO("Not yet implemented")
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHeroeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): HeroeRepository {
        TODO("Not yet implemented")
    }


}