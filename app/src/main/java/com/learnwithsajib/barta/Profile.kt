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


        var userEmail:String=requireArguments().getString("email").toString()
        var userName:String=requireArguments().getString("name").toString()
        var userContact:String=requireArguments().getString("contact").toString()
        var userPassword:String=requireArguments().getString("password").toString()

        fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)


        binding.emailetid.text=userEmail.toEditable()
        binding.Nameetid.text=userName.toEditable()
        binding.contactetid.text=userContact.toEditable()
        binding.Passwordetid.text=userPassword.toEditable()




   return binding.root


    }


}