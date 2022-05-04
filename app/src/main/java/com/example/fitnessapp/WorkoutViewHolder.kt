package com.example.fitnessapp

import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.databinding.ListItemLayoutBinding

class WorkoutViewHolder(val binding: ListItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentWorkout: Workout
    fun bindWorkout(workout: Workout) {
        currentWorkout = workout
        binding.performText.text = currentWorkout.workoutName
        binding.weightTextview.text = currentWorkout.weight.toString()
        binding.repText.text=currentWorkout.reps
    }

}