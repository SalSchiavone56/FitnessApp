package com.example.fitnessapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.databinding.ListItemLayoutBinding


class WorkoutViewHolder(val binding: ListItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentWorkout: Workout
    fun bindWorkout(workout: Workout) {
        currentWorkout = workout
        binding.performText.text = currentWorkout.workoutName
        if (currentWorkout.weight==0){
            binding.weightTextview.text=""
        }
        else {
            binding.weightTextview.text = "Weight ${currentWorkout.weight} lbs"
        }
        binding.repText.text=currentWorkout.reps
        val browserIntent = Intent (Intent.ACTION_VIEW, Uri.parse(currentWorkout.link))

            binding.root.setOnClickListener{

                binding.root.context.startActivity(browserIntent)
            }


    }


}