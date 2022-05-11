package com.example.fitnessapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.fitnessapp.databinding.FragmentWorkoutChoiceBinding
import kotlin.math.roundToInt

class WorkoutChoiceFragment : Fragment() {
    private var _binding: FragmentWorkoutChoiceBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkoutChoiceBinding.inflate(inflater, container, false)
        val rootview = binding.root
        val args = WorkoutChoiceFragmentArgs.fromBundle(requireArguments())
        var w=args.userArg.weight
        if(!args.userArg.gender)
            w /= 1.6.toInt()

        if(args.userArg.age>=61) {
            w /= 1.5.toInt()
        }
        else if(args.userArg.age in 40..60) {
            w /= 1.2.toInt()
        }

        binding.buildMuscleButton.setOnClickListener {
            val benchPress = bench(w)
            val squat=squat(w)
            val press=shoulderPress(w)
            val deadlift=deadlift(w)
            val curl=curl(w)
            val lunge=lunge(w)
            val action = WorkoutChoiceFragmentDirections.actionWorkoutChoiceFragmentToCalculatedWorkout(benchPress,squat,press,deadlift,curl,lunge)
            rootview.findNavController().navigate(action)
        }
        binding.maintainMuscleButton.setOnClickListener{
            w /= 1.15.roundToInt()
            val bench=bench(w)
            val squat=squat(w)
            val lunge=lunge(w)
            val curl=curl(w)
            val pushUps=pushUp()
            val sitUps=sitUp()
            val action = WorkoutChoiceFragmentDirections.actionWorkoutChoiceFragmentToCalculatedWorkout(bench,squat,lunge,curl,pushUps, sitUps)
            rootview.findNavController().navigate(action)
        }
        binding.cardioButton.setOnClickListener{
            val rope=jumpRope()
            val run=run(args.userArg.age, args.userArg.gender)
            val burpees=burpees()
            val action = WorkoutChoiceFragmentDirections.actionWorkoutChoiceFragmentToCalculatedWorkout(rope, run, burpees, run, run, run)
            rootview.findNavController().navigate(action)
        }
        binding.dietButton.setOnClickListener {
            val action = WorkoutChoiceFragmentDirections.actionWorkoutChoiceFragmentToDietFragment()
            rootview.findNavController().navigate(action)
        }

        return rootview
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun bench(weigh:Int): Workout {
        val w = "Bench Press"
        val r = "4 sets of 6-8"
        val weight:Int
            if (weigh <= 150)
                weight = weigh - 20
            else weight = weigh - 17

        return Workout(w, weight, r)
    }
    fun squat(weigh:Int): Workout {
        val w = "Squat"
        val r = "4 sets of 6-8"
        val weight: Int
            if (weigh <= 150)
                weight = weigh+15
            else weight = weigh*1.2.toInt()

        return Workout(w, weight, r)
    }
    fun shoulderPress(weigh:Int): Workout {
        val w = "Shoulder Press"
        val r = "3 sets of 10-12"
        val weight: Int
            if (weigh<= 150)
                weight = (weigh*0.34).toInt()
            else weight = (weigh*0.42).toInt()

        return Workout(w, weight, r)
    }
    fun deadlift(weigh:Int): Workout {
        val w = "Deadlift"
        val r = "3 sets of 5-8"
        val weight: Int
            if (weigh <= 150)
                weight = weigh*1.38.toInt()
            else weight = weigh*1.43.toInt()

        return Workout(w, weight, r)
    }
    fun curl(weigh:Int): Workout {
        val w = "Dumbbell Curl"
        val r = "3 sets of 8-10"
        val weight: Int
            if (weigh <= 150)
                weight = weigh*0.2.toInt()
            else weight = weigh*0.25.toInt()

        return Workout(w, weight, r)
    }
    fun lunge(weigh:Int): Workout {
        val w = "Dumbbell Lunge"
        val r = "3 sets of 5-8 Each Leg"
        val weight: Int
            if (weigh <= 150)
                weight = weigh*0.4.toInt()
            else weight = weigh*0.43.toInt()

        return Workout(w, weight, r)
    }
    fun pushUp(): Workout {
        return Workout("Push-ups", 0, "2 sets until failure")
    }
    fun sitUp(): Workout {
        return Workout("Sit-ups", 0, "2 sets until failure")
    }
    fun jumpRope(): Workout{
        return Workout("Jump-Rope", 0, "3 sets of 1 minute Straight")
    }
    fun run(age:Int, g:Boolean):Workout{
        var x=0
        if (age<20)
            x=405
        else if (age in 20..39)
            x=400
        else if (age in 40..59)
            x=452
        else if (age in 60..70)
            x=512
        else x=620
        if(!g && age<60)
            x += 70
        else if (!g)
            x += 140
        val s="Average Mile Run Time for Weight/Gender: ${x/60} minutes ${x%60}seconds"
        return Workout("1 Mile Run",0, s)
    }
    fun burpees(): Workout{
        return Workout("Burpees", 0,"2 sets of 15" )
    }

}