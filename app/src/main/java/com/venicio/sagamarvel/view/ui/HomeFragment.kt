package com.venicio.sagamarvel.view.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.venicio.sagamarvel.R
import com.venicio.sagamarvel.data.repository.MovieRepository
import com.venicio.sagamarvel.databinding.FragmentHomeBinding
import com.venicio.sagamarvel.view.adapter.SagaMarvelAdapter
import com.venicio.sagamarvel.viewmodel.SagaMarvelViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerAdapter: SagaMarvelAdapter
    private val sViewModel: SagaMarvelViewModel by viewModel {
        parametersOf(MovieRepository(get()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        setHasOptionsMenu(true)
        setupHomeRecycler()
        setupObserver()

        return (binding.root)
    }

    private fun setupObserver() {
        sViewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            setupHomeRecycler()
            recyclerAdapter.submitList(it)

            binding.progressBarHome.visibility = View.GONE
            binding.rvAllMovies.visibility = View.VISIBLE
        })
    }

    private fun setupHomeRecycler() {
        val recycler = binding.rvAllMovies
        recycler.setHasFixedSize(true)
        recyclerAdapter = SagaMarvelAdapter()
        recycler.adapter = recyclerAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_marvel, menu)

        val menuItem: MenuItem = menu.findItem(R.id.ic_search)
        var search = menuItem.actionView


        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val direction = HomeFragmentDirections.actionHomeFragmentToFavoriteFragment()

        when (item.itemId) {
            R.id.ic_favorite -> findNavController().navigate(direction)
            R.id.ic_filter_name -> sViewModel.sortByName.observe(viewLifecycleOwner, {recyclerAdapter.submitList(it)})
            R.id.ic_filter_year -> sViewModel.sortByYear.observe(viewLifecycleOwner, {recyclerAdapter.submitList(it)})
        }
        return super.onOptionsItemSelected(item)
    }

}