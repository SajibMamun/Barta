package com.learnwithsajib.barta

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.learnwithsajib.barta.ModelClass.User
import com.learnwithsajib.barta.databinding.FragmentOnlineBinding


class Online : Fragment() {

    lateinit var binding: FragmentOnlineBinding
    lateinit var mAuth: FirebaseAuth
    var firebaseUser: FirebaseUser? = null
    var userList = mutableListOf<User>()
    lateinit var adapter: UserAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        adapter=UserAdapter()
        binding = FragmentOnlineBinding.inflate(layoutInflater, container, false)

        mAuth = FirebaseAuth.getInstance()


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseDatabase.getInstance().reference.child("User").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                snapshot.children.forEach {
                    var user:User=it.getValue(User::class.java)!!

                    userList.add(user)

                }


                adapter.submitList(userList)

                binding.Recyclerviewid.adapter = adapter


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}