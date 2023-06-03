package com.learnwithsajib.barta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.learnwithsajib.barta.databinding.FragmentOnlineBinding


class Online : Fragment(), UserAdapter.UserListener {

    lateinit var binding: FragmentOnlineBinding
    lateinit var mAuth: FirebaseAuth
    var firebaseUser: FirebaseUser? = null
    var userList = mutableListOf<User>()
    lateinit var adapter: UserAdapter
    lateinit var userSenderuid:String
    lateinit var userSenderImg:String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        adapter = UserAdapter(this@Online)
        binding = FragmentOnlineBinding.inflate(layoutInflater, container, false)

        mAuth = FirebaseAuth.getInstance()
        firebaseUser=FirebaseAuth.getInstance().currentUser





        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        //get data from database
        FirebaseDatabase.getInstance().reference.child("User").child(mAuth.uid.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    var user: User = snapshot.getValue(User::class.java)!!

                    userSenderuid=user.userId
                    userSenderImg=user.profileImgUrl


                }

                override fun onCancelled(error: DatabaseError) {

                }

            })




        FirebaseDatabase.getInstance().reference.child("User")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    userList.clear()
                    snapshot.children.forEach {
                        var user: User = it.getValue(User::class.java)!!



                        if(firebaseUser!=null)
                        {
                            if(user.userId!=firebaseUser!!.uid){
                                userList.add(user)
                            }
                        }


                    }


                    adapter.submitList(userList)

                    binding.Recyclerviewid.adapter = adapter


                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }


    override fun moveusertoConversation(user: User) {

        var userEmail=user.email
        var userName=user.name
        var userContact=user.contact
        var userPassword=user.password
        var userImage=user.profileImgUrl


        var bundle1=Bundle()
        bundle1.putString("uid",user.userId)
        bundle1.putString("email",userEmail)
        bundle1.putString("name",userName)
        bundle1.putString("contact",userContact)
        bundle1.putString("password",userPassword)
        bundle1.putString("image",userImage)
        bundle1.putString("senderuid",userSenderuid)
        bundle1.putString("senderimg",userSenderImg)


        findNavController().navigate(R.id.action_online_to_chat_Fragment,bundle1)
    }

    override fun moveUser(user: User) {

        //data pass
        var userEmail=user.email
        var userName=user.name
        var userContact=user.contact
        var userPassword=user.password
        var userImage=user.profileImgUrl


        var bundle=Bundle()

        bundle.putString("email",userEmail)
        bundle.putString("name",userName)
        bundle.putString("contact",userContact)
        bundle.putString("password",userPassword)
        bundle.putString("image",userImage)





        findNavController().navigate(R.id.action_online_to_useDetails,bundle)

    }

}