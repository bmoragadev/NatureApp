package com.example.natureapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.natureapp.R
import com.example.natureapp.databinding.FragmentListBinding
import com.example.natureapp.model.Plant
import com.example.natureapp.viewmodel.PlantViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!

    private val sharedViewModel: PlantViewModel by activityViewModels()
    private lateinit var plantAdapter: PlantAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

        sharedViewModel.fetchPlants()

        binding.fabAddPlant.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addPlantFragment)
        }
    }

    private fun setupRecyclerView(): Unit {
        plantAdapter = PlantAdapter(emptyList<Plant>()) { plant: Plant ->
            val bundle: Bundle = Bundle().apply {
                putString("plant_name", plant.name)
                putString("plant_desc", plant.description)
                putInt("plant_img", plant.imageResId)
            }
            findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }

        binding.rvPlants.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = plantAdapter
        }
    }

    private fun setupObservers(): Unit {
        sharedViewModel.plants.observe(viewLifecycleOwner, Observer<List<Plant>> { plantList: List<Plant>? ->
            plantList?.let { plants: List<Plant> ->
                plantAdapter.updateData(plants)
            }
        })

        sharedViewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> { isLoading: Boolean ->
            binding.progressHorizontal.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.rvPlants.visibility = if (isLoading) View.GONE else View.VISIBLE
        })
    }

    override fun onDestroyView(): Unit {
        super.onDestroyView()
        _binding = null
    }
}