package com.learnwithsajib.barta

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learnwithsajib.barta.databinding.FragmentOnlineBinding
import com.learnwithsajib.barta.databinding.FragmentProfileBinding


class Profile : Fragment() {
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)







   return binding.root


    }


}