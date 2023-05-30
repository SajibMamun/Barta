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
    lateinit var mauth:FirebaseAuth
    lateinit var myref: DatabaseReference
    lateinit var firebaseUser: FirebaseUser
    lateinit var uid:String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mauth= FirebaseAuth.getInstance()
        val database= Firebase.database
        myref=database.reference.child("User")



        binding.loginheretv.setOnClickListener {

            var intent= Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
        }
        
        
        binding.registerbtnid.setOnClickListener { 
            var name:String=binding.Nameetid.text.toString().trim()
            var contact:String=binding.contactetid.text.toString().trim()
            var email:String=binding.emailetid.text.toString().trim()
            var password:String=binding.Passwordetid.text.toString().trim()
            var confirmpass:String=binding.ConfirmPasswordetid.text.toString().trim()
        
        if(name.isEmpty())
        {
            binding.Nameetid.error="Enter Name"
        }
            else if(contact.isEmpty()|| contact.length!=11)
        {
                binding.contactetid.error="Enter valid Number"
        }   
        else if(email.isEmpty())
        {
                binding.contactetid.error="Enter Email"
        }
        else if(password.isEmpty()||password.length!=8)
        {
            binding.contactetid.error="Enter 8 Digit Password"
        }
        else if(confirmpass.isEmpty()|| password != confirmpass)
        {
            binding.contactetid.error="Enter 8 Digit Password"
        }
            else
        {
            RegisterUser(email,password,name,contact)
        }
    
        
        }
    }

    private fun RegisterUser(email: String, password: String, name: String, contact: String) {
mauth.createUserWithEmailAndPassword(email,password)
    .addOnCompleteListener {
        if(it.isSuccessful)
        {
            firebaseUser=FirebaseAuth.getInstance().currentUser!!
            uid=firebaseUser.uid

            val map= mapOf("name" to name, "email" to email, "phone" to contact, "password" to password)

            if(uid!=null)

            {
                myref.child(uid).setValue(map).addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(applicationContext,"Welcome",Toast.LENGTH_SHORT).show()
                        var intent= Intent(applicationContext,MainActivity::class.java)
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"${it.exception.toString()}",Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
    }
    }


}