package com.learnwithsajib.barta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.learnwithsajib.barta.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    lateinit var mAuth: FirebaseAuth
    lateinit var myRef: DatabaseReference
    lateinit var userId: String
    lateinit var firebaseUser: FirebaseUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        val database = Firebase.database
        myRef = database.reference.child("User")




        binding.loginheretv.setOnClickListener {

            var intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }


        binding.registerbtnid.setOnClickListener {
            var name: String = binding.Nameetid.text.toString().trim()
            var contact: String = binding.contactetid.text.toString().trim()
            var email: String = binding.emailetid.text.toString().trim()
            var password: String = binding.Passwordetid.text.toString().trim()
            var confirmpass: String = binding.ConfirmPasswordetid.text.toString().trim()

            if (name.isEmpty()) {
                binding.Nameetid.error = "Enter Name"
            } else if (contact.isEmpty() || contact.length != 11) {
                binding.contactetid.error = "Enter valid Number"
            } else if (email.isEmpty()) {
                binding.contactetid.error = "Enter Email"
            } else if (password.isEmpty() || password.length != 8) {
                binding.contactetid.error = "Enter 8 Digit Password"
            } else if (confirmpass.isEmpty() || password != confirmpass) {
                binding.contactetid.error = "Enter 8 Digit Password"
            } else {
                registerUser(name, email, contact, password)
            }


        }
    }

    private fun registerUser(name: String, email: String, contact: String, password: String) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "Register Successfully", Toast.LENGTH_SHORT)
                        .show()
                    firebaseUser = FirebaseAuth.getInstance().currentUser!!
                    userId = firebaseUser.uid

                    val map = mapOf(
                        "name" to name,
                        "email" to email,
                        "contact" to contact,
                        "password" to password
                    )


                    if (userId != null) {
                        myRef.child(userId).setValue(map).addOnCompleteListener {


                     if(it.isSuccessful)
                     {

                         Toast.makeText(
                             applicationContext, "Welcome ${name}", Toast.LENGTH_SHORT
                         ).show()
                         var intent = Intent(applicationContext, MainActivity::class.java)
                         startActivity(intent)
                     }

                        }
                    }
                } else {
                    Toast.makeText(
                        applicationContext, "${it.exception?.message}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


}