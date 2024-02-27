package com.example.absolutegame.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.absolutegame.Application
import com.example.absolutegame.presentation.adapter.game.GameAdapter
import com.example.absolutegame.databinding.FragmentHomeBinding
import com.example.absolutegame.presentation.blur.BlurViewModel
//import com.example.absolutegame.di.ViewModelFactory
import com.example.domain.Game
import com.example.absolutegame.presentation.home.HomeViewModel
import javax.inject.Inject

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var gameAdapter: GameAdapter? = null

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        setupGameAdapter()

        viewModel.fetchGames()
    }

    private fun setupGameAdapter() {
        gameAdapter = GameAdapter(requireContext(), viewModel)
        binding?.recyclerGame?.adapter = gameAdapter
        binding?.recyclerGame?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeLiveData() {
        viewModel.loading.observe(viewLifecycleOwner, ::handleLoading)
        viewModel.error.observe(viewLifecycleOwner, ::handleError)
        viewModel.games.observe(viewLifecycleOwner, ::handleGames)
    }

    private fun handleLoading(isLoading: Boolean) {
        binding?.progress?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun handleError(error: String?) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        Log.d("testt", error.toString())
    }

    private fun handleGames(games: List<com.example.domain.Game>) {
        gameAdapter?.submitList(games)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}