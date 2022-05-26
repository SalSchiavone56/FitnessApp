package com.example.fitnessapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.fitnessapp.databinding.CalculatedWorkoutBinding


class CalculatedWorkout : Fragment() {
    private var _binding: CalculatedWorkoutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CalculatedWorkoutBinding.inflate(inflater, container, false)
        val rootview=binding.root
        val args=CalculatedWorkoutArgs.fromBundle(requireArguments())
        val list: List<Workout>
        if (args.w4Arg.weight==0) {
            list = listOf(args.w1Arg, args.w2Arg, args.w3Arg)
        }
        else {
            list = listOf(args.w1Arg, args.w2Arg, args.w3Arg, args.w4Arg, args.w5Arg, args.w6Arg)
        }
        val mAdapter=WorkoutAdapter(list)
        binding.recyclerView.adapter=mAdapter
        binding.doneButton.setOnClickListener{
            val action = CalculatedWorkoutDirections.actionCalculatedWorkoutToCongratsFragment(args.nameArg, args.typeArg)
            rootview.findNavController().navigate(action)
        }

        return rootview
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}