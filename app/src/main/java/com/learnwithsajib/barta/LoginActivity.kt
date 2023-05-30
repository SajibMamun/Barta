package com.learnwithsajib.barta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learnwithsajib.barta.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.loginbtnid.setOnClickListener {

            var intent=Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }


        binding.registerheretv.setOnClickListener {

            var intent=Intent(applicationContext,RegisterActivity::class.java)
            startActivity(intent)
        }




        binding.loginbtnid.setOnClickListener {

            var email:String=binding.Emailetid.text.toString().trim()
            var password:String=binding.Passwordetid.text.toString().trim()

            if(email.isEmpty())
            {
                binding.Emailetid.error="Enter Email"
            }
            else if(password.isEmpty()||password.length!=8)
            {
                binding.Passwordetid.error="Enter Valid Password"
            }
            else
            {
                SigninUser(email,password)
            }
        }
    }

    private fun SigninUser(email: String, password: String) {

    }
}