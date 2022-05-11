package com.example.fitnessapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Workout (val workoutName: String, val weight:Int, val reps:String): Parcelable