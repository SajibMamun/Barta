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
    }
}