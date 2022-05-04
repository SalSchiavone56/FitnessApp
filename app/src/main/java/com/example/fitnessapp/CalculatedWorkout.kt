package com.example.fitnessapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.fitnessapp.databinding.FragmentCalculatedWorkoutBinding
import com.example.fitnessapp.databinding.FragmentWorkoutChoiceBinding


class CalculatedWorkout : Fragment() {
    private var _binding: FragmentCalculatedWorkoutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatedWorkoutBinding.inflate(inflater, container, false)
        val rootview=binding.root
        binding.doneButton.setOnClickListener{
            val action = CalculatedWorkoutDirections.actionCalculatedWorkoutToCongratsFragment()
            rootview.findNavController().navigate(action)
        }

        return rootview
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}