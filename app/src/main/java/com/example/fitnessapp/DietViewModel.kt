package com.example.fitnessapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DietViewModel : ViewModel() {
    private var _totalCalories= MutableLiveData(0)
    val totalCalories: LiveData<Int>
    get() = _totalCalories

    private var _caloriesLeft= MutableLiveData(1)
    val caloriesLeft: LiveData<Int>
        get() = _caloriesLeft

    fun addCalories(calValue:Int){
        val currentCal=totalCalories.value ?:0
        _totalCalories.value =currentCal+calValue
    }
    fun calculateRemaining(subtractValue:Int){
        val currentCal=totalCalories.value ?:0
        _caloriesLeft.value=subtractValue-currentCal
    }
    fun resetCalories(){
        _totalCalories.value=0
    }
}