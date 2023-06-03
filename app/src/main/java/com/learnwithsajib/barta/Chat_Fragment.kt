package com.learnwithsajib.barta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.learnwithsajib.barta.databinding.FragmentChatBinding


class Chat_Fragment : Fragment() {
    lateinit var binding: FragmentChatBinding
    var firebaseUser: FirebaseUser? = null
    var chats: MutableList<Chat> = mutableListOf()
    lateinit var firebaseDatabaseReference: DatabaseReference
    lateinit var mAuth: FirebaseAuth
    var currentUserID=""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater, container, false)



        FirebaseAuth.getInstance().currentUser?.let {
            firebaseUser=it
            currentUserID=it.uid
        }






        var recieverUID: String = requireArguments().getString("uid").toString()
        var recieverImage: String = requireArguments().getString("image").toString()
        var SenderUID: String = requireArguments().getString("senderuid").toString()
        var SenderIMG: String = requireArguments().getString("senderimg").toString()

        binding.receiverImgid.load(recieverImage)
        binding.senderImgid.load(SenderIMG)

        val database = Firebase.database
        firebaseDatabaseReference = database.reference.child("chat")


firebaseDatabaseReference.addValueEventListener(object : ValueEventListener{
    override fun onDataChange(snapshot: DataSnapshot) {


        chats.clear()
        snapshot.children.forEach {
            val chat: Chat = it.getValue<Chat>(Chat::class.java)!!


            if (chat.sender == currentUserID && chat.receiver == recieverUID || chat.sender == recieverUID && chat.receiver == currentUserID)
            {
                chats.add(chat)

            }
        }
        var adapter = ChatAdapter(chats, currentUserID)

        binding.chatRecyclerviewid.adapter = adapter
    }
    override fun onCancelled(error: DatabaseError) {

    }

} )







        binding.sendbtnid.setOnClickListener {
            val messageId = firebaseDatabaseReference.push().key!!



                val message = Chat(
                    binding.writeTextetid.text.toString().trim(),
                    SenderUID,
                    recieverUID,
                    messageId

                )

                firebaseDatabaseReference.child(messageId).setValue(message)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Message Sent", Toast.LENGTH_SHORT).show()
                        binding.writeTextetid.setText("")
                    }

            }





        return binding.root
    }


}