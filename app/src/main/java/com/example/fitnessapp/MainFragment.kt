package com.example.fitnessapp

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.fitnessapp.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*


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
        binding.BRadiobutton.isChecked=true
        binding.ARadiobutton.setOnClickListener {
            gender=true
        }
        binding.BRadiobutton.setOnClickListener {
            gender=false
        }

        binding.nextButton.setOnClickListener {
            var b = 0
            if (binding.ageView.text.toString() != "" && binding.heightView.text.toString() != "") {
                if (binding.weightView.text.toString() != "" && binding.nameView.text.toString() != "") {
                    val age = Integer.parseInt(binding.ageView.text.toString())
                    val height = Integer.parseInt(binding.heightView.text.toString())
                    val weight = Integer.parseInt(binding.weightView.text.toString())
                    val username = binding.nameView.text.toString()
                    val user = User(username, age, height, weight, gender)
                    val action =
                        MainFragmentDirections.actionMainFragmentToWorkoutChoiceFragment(user)
                    rootview.findNavController().navigate(action)
                } else Toast.makeText(getContext(), R.string.toast_message, Toast.LENGTH_LONG)
                    .show()
            }
            else Toast.makeText(getContext(), R.string.toast_message, Toast.LENGTH_LONG)
                .show()
        }
        binding.dumbellView.setOnClickListener{
            binding.dumbellView.startAnimation(AnimationUtils.loadAnimation(this.context, androidx.appcompat.R.anim.abc_shrink_fade_out_from_bottom))

        }

        return rootview
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}