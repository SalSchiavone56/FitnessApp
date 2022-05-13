package com.example.fitnessapp

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.databinding.ListItemLayoutBinding
import java.security.AccessController.getContext

class WorkoutViewHolder(val binding: ListItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentWorkout: Workout
    val browserIntent = Intent (Intent.ACTION_VIEW, Uri.parse(currentWorkout.link))
    init{
        binding.root.setOnClickListener{
            startActivity(this.getContext(browserIntent))
        }

    }
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
    }

}