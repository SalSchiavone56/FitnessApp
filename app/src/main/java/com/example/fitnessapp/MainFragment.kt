package com.example.fitnessapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.fitnessapp.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootview=binding.root

        var gender=false
        binding.ARadiobutton.setOnClickListener {
            gender=true
        }
        binding.BRadiobutton.setOnClickListener {
            gender=false
        }

        binding.nextButton.setOnClickListener{
            val username=binding.nameView.text.toString()
            val age=Integer.parseInt(binding.ageView.text.toString())
            val height=Integer.parseInt(binding.heightView.text.toString())
            val weight=Integer.parseInt(binding.weightView.text.toString())
            val user=User(username, age, height, weight, gender)
            val action = MainFragmentDirections.actionMainFragmentToWorkoutChoiceFragment(user)
            rootview.findNavController().navigate(action)
        }
        return rootview
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}