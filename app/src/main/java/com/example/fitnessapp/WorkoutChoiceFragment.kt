package com.example.fitnessapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.fitnessapp.databinding.FragmentWorkoutChoiceBinding

class WorkoutChoiceFragment : Fragment() {
    private var _binding: FragmentWorkoutChoiceBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkoutChoiceBinding.inflate(inflater, container, false)
        val rootview=binding.root
        val args=WorkoutChoiceFragmentArgs.fromBundle(requireArguments())
//        val benchWeight=args.userArg.weight.toInt()
        binding.buildMuscleButton.setOnClickListener{
            val action = WorkoutChoiceFragmentDirections.actionWorkoutChoiceFragmentToCalculatedWorkout()
            rootview.findNavController().navigate(action)
        }
        binding.dietButton.setOnClickListener{
            val action = WorkoutChoiceFragmentDirections.actionWorkoutChoiceFragmentToDietFragment()
            rootview.findNavController().navigate(action)
        }

        return rootview
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}