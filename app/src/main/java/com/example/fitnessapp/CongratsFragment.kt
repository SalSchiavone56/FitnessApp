package com.example.fitnessapp

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.fitnessapp.databinding.FragmentCongratsBinding


class CongratsFragment : Fragment() {
    private var _binding: FragmentCongratsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCongratsBinding.inflate(inflater, container, false)
        val args=CongratsFragmentArgs.fromBundle(requireArguments())
        var s: String
        if(args.typerArg==0){
            s="Muscle Build"
        }
        else if(args.typerArg==1){
            s="Maintain Muscle"
        }
        else s="Cardio"
        val REQUEST_IMAGE_CAPTURE = 1

        fun dispatchTakePictureIntent() {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                // display error state to the user
            }
        }

        binding.welcomeText.text="Congratulations ${args.finisherArg}! You Completed your ${s} Workout!"
        binding.imageButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_TEXT, "Today I did a ${s} workout. You should try FitnessApp Sometime!")
                putExtra(Intent.EXTRA_SUBJECT, "I Finished my Workout on Fitness App!")
                putExtra(Intent.EXTRA_EMAIL, "dinosrock1@gmail.com")
            }
            startActivity(intent)

        }
        binding.rocky.setOnClickListener{
            binding.rocky.startAnimation(AnimationUtils.loadAnimation(this.context, androidx.appcompat.R.anim.abc_slide_out_top))

        }
        binding.cam.setOnClickListener{
            dispatchTakePictureIntent()

        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}