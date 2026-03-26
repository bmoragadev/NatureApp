package com.example.natureapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.natureapp.R
import com.example.natureapp.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val plantName = arguments?.getString("plant_name")?: "Sin Nombre"
        val plantDesc = arguments?.getString("plant_desc")?: "Sin descripcion"
        val plantImage = arguments?.getInt("plant_img")?: R.drawable.ic_launcher_foreground

        binding.tvDetailName.text = plantName
        binding.tvDetailDescription.text = plantDesc
        binding.ivDetailImage.setImageResource(plantImage)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}