package com.example.fitnessapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(val username: String, val age: Int, val height: Int, val weight: Int, val gender: Boolean):Parcelable