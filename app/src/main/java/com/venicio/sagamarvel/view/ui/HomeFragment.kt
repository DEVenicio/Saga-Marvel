package com.venicio.sagamarvel.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.venicio.sagamarvel.data.model.Movies
import com.venicio.sagamarvel.data.repository.SagaMarvelRepository
import com.venicio.sagamarvel.databinding.FragmentHomeBinding
import com.venicio.sagamarvel.view.adapter.SagaMarvelAdapter
import com.venicio.sagamarvel.viewmodel.SagaMarvelViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment() {

     private lateinit var binding: FragmentHomeBinding
     private lateinit var recyclerAdapter: SagaMarvelAdapter
     private val sViewModel: SagaMarvelViewModel by viewModel {
         parametersOf(SagaMarvelRepository())
     }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        setupRecycler()
        setupObserver()

        return (binding.root)
    }

    private fun setupObserver() {
        sViewModel.allMovies.observe(viewLifecycleOwner, Observer {
            setupRecycler()
            recyclerAdapter.submitList(it)

            binding.progressBarHome.visibility = View.GONE
            binding.rvAllMovies.visibility = View.VISIBLE
        })
    }

    private fun setupRecycler() {
       val recycler = binding.rvAllMovies
        recycler.setHasFixedSize(true)
        recyclerAdapter = SagaMarvelAdapter()
        recycler.adapter = recyclerAdapter
    }

}