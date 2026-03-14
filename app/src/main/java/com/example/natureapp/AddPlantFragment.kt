package com.example.natureapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.natureapp.databinding.FragmentAddPlantBinding

class AddPlantFragment : Fragment() {

    private var _binding : FragmentAddPlantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddPlantBinding.inflate(inflater, container, false)


        binding.btnSave.setOnClickListener {
            val name = binding.etPlantName.text.toString().trim()
            val desc = binding.etPlantDesc.text.toString().trim()

            if(name.isNotEmpty()){

                val resultBundle = Bundle().apply {
                    putString("new_plant_name", name)
                    putString("new_plant_desc", desc)
                }

                parentFragmentManager.setFragmentResult("add_plant_request", resultBundle)

                Toast.makeText(requireContext(), "$name guardada exitosamente", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }else{
                Toast.makeText(requireContext(), "Por favor, ingresa un nombre", Toast.LENGTH_SHORT).show()
            }
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}