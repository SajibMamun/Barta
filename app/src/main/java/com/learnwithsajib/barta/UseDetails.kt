package com.learnwithsajib.barta

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.learnwithsajib.barta.databinding.FragmentUseDetailsBinding


class UseDetails : Fragment() {
    lateinit var binding: FragmentUseDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding=FragmentUseDetailsBinding.inflate(layoutInflater,container,false)



        var userEmail:String=requireArguments().getString("email").toString()
        var userName:String=requireArguments().getString("name").toString()
        var userContact:String=requireArguments().getString("contact").toString()
        var userPassword:String=requireArguments().getString("password").toString()
        var userImage:String=requireArguments().getString("image").toString()


        binding.emailetid.text=userEmail
        binding.Nameetid.text=userName
        binding.contactetid.text=userContact
        binding.Passwordetid.text=userPassword
        binding.profileimgid.load(userImage)

        return binding.root
    }


    }
