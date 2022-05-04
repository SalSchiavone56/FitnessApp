package com.example.fitnessapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.databinding.ListItemLayoutBinding

class WorkoutAdapter(val workoutList: List<Workout>) : RecyclerView.Adapter<WorkoutViewHolder>()  {
    override fun getItemCount(): Int {
        return workoutList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val binding= ListItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WorkoutViewHolder(binding)
    }
    override fun onBindViewHolder(currentViewHolder: WorkoutViewHolder, position: Int) {
        val currentWorkout = workoutList[position]
        currentViewHolder.bindWorkout(currentWorkout)
    }

}