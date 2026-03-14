package com.example.natureapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.natureapp.databinding.FragmentListBinding
import models.Plant

class ListFragment : Fragment() {

    private var _binding : FragmentListBinding? = null
    private val binding get() = _binding!!

    private val myPlants = mutableListOf<Plant>(
        Plant(
            1,
            "Aloe Vera",
            "Riego escaso, propagar por hijuelos.",
            R.drawable.ic_tree
        ),
        Plant(2, "Monstera Deliciosa", "Luz indirecta brillante. Humedad alta.", R.drawable.ic_tree),
        Plant(3, "Suculenta Echeveria", "Mucho sol, sustrato con buen drenaje.", R.drawable.ic_tree)
    )



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(layoutInflater, container, false)


        val adapter = PlantAdapter(myPlants) { plant ->

            val bundle = Bundle().apply {
                putString("plant_name", plant.name)
                putString("plant_desc", plant.description)
                putInt("plant_img", plant.imageResId)
            }
            findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)

        }

        binding.rvPlants.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPlants.adapter = adapter


        parentFragmentManager.setFragmentResultListener("add_plant_request", viewLifecycleOwner) { _, bundle ->
            val newName = bundle.getString("new_plant_name")
            val newDesc = bundle.getString("new_plant_desc")

            val newId = myPlants.size + 1
            val newPlant = Plant(newId, newName!!, newDesc!!, R.drawable.ic_tree)
            myPlants.add(newPlant)

            adapter.notifyItemInserted(myPlants.size - 1)
        }



        binding.fabAddPlant.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addPlantFragment)
        }

        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}