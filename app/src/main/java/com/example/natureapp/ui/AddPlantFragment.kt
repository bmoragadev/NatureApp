package com.example.natureapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.natureapp.databinding.FragmentAddPlantBinding
import com.example.natureapp.model.Plant
import com.example.natureapp.viewmodel.PlantViewModel

class AddPlantFragment : Fragment() {

    private var _binding : FragmentAddPlantBinding? = null
    private val binding get() = _binding!!


    private val sharedViewModel : PlantViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddPlantBinding.inflate(inflater, container, false)


        binding.btnSave.setOnClickListener {
            val name = binding.etPlantName.text.toString().trim()
            val desc = binding.etPlantDesc.text.toString().trim()

            if(name.isNotEmpty()){

                val newPlant : Plant = Plant(id = 0, name = name, description = desc)


                sharedViewModel.addPlant(newPlant)

                Toast.makeText(requireContext(), "$name guardada exitosamente", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }else{
                Toast.makeText(requireContext(), "Por favor, ingresa un nombre", Toast.LENGTH_SHORT).show()
            }
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}