package com.example.fitnessapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.fitnessapp.databinding.FragmentDietBinding
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class DietFragment : Fragment() {
    private var _binding: FragmentDietBinding? = null
    private val binding get() = _binding!!
    private val viewModel:DietViewModel by viewModels()
    lateinit var s:String
    lateinit var c:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDietBinding.inflate(inflater, container, false)

        val args=DietFragmentArgs.fromBundle(requireArguments())
        var totCal:Int
        s=""
        c=""
        viewModel.totalCalories.observe(viewLifecycleOwner){newCal->
            if(newCal>args.caloriesArg)
                binding.total.text="Total Calories:${args.caloriesArg}"
            else
            binding.total.text="Total Calories:${newCal}"

        }
        viewModel.caloriesLeft.observe(viewLifecycleOwner){amount->
            if (amount<0)
                binding.leftCalories.text = "0 left"
            else {
                if (viewModel.totalCalories.value == 0)
                    binding.leftCalories.text = "${args.caloriesArg} left"
                else binding.leftCalories.text = "${amount} left"
            }

        }
        binding.welcomeText.text="Maintenance Calories: ${args.caloriesArg} cal"
        binding.addButton.setOnClickListener {
            if (binding.foodText.text.isEmpty() || binding.calText.text.isEmpty()) {
                Toast.makeText(getContext(), R.string.toast_mes, Toast.LENGTH_LONG)
                    .show()
            } else {
                totCal = binding.calText.text.toString().toInt()
                viewModel.addCalories(totCal)
                viewModel.calculateRemaining(args.caloriesArg)
                if (viewModel.caloriesLeft.value ?: 0 < 0) {
                    val snackBar = Snackbar.make(
                        binding.relativeLayout,
                        R.string.reached,
                        Snackbar.LENGTH_SHORT
                    )
                    snackBar.show()

                } else {
                    s += "+${binding.foodText.text}\n"
                    c += "${binding.calText.text} calories\n"
                    binding.mealsText.text = s
                    binding.caloriesTextview.text = c
                }


            }
        }
        binding.resetButton.setOnClickListener {
            viewModel.resetCalories()
            s = ""
            c = ""
            binding.mealsText.text=s
            binding.caloriesTextview.text=c
            viewModel.calculateRemaining(viewModel.totalCalories.value ?: 0)

        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}