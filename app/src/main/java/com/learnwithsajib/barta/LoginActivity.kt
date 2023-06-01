package com.learnwithsajib.barta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.learnwithsajib.barta.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var mAuth: FirebaseAuth
    private var firebaseUser: FirebaseUser? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)




        mAuth = FirebaseAuth.getInstance()
        firebaseUser = FirebaseAuth.getInstance().currentUser


        if (firebaseUser != null) {
            var intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }


        binding.registerheretv.setOnClickListener {

            var intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }




        binding.loginbtnid.setOnClickListener {

            var email: String = binding.Emailetid.text.toString().trim()
            var password: String = binding.Passwordetid.text.toString().trim()

            if (email.isEmpty()) {
                binding.Emailetid.error = "Enter Email"
            } else if (password.isEmpty() || password.length != 8) {
                binding.Passwordetid.error = "Enter Valid Password"
            } else {
                SigninUser(email, password)
            }
        }
    }

    private fun SigninUser(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "Welcome!", Toast.LENGTH_LONG)
                        .show()
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(applicationContext, "${it.exception?.message}", Toast.LENGTH_LONG)
                        .show()
                }

            }


    }


}
