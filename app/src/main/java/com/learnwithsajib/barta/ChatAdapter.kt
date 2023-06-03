package com.learnwithsajib.barta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(var chats: List<Chat>, var currentUserID: String) :
    RecyclerView.Adapter<ChatViewHolder>() {


    private val RECEIVER = 1
    private val SENDER = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        if (viewType == SENDER) {
            var view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chatui_sender, parent, false)
            return ChatViewHolder(view)


        } else {
            var view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chatui_receiver, parent, false)
            return ChatViewHolder(view)
        }


    }

    override fun getItemCount(): Int {
        return chats.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        var chat: Chat = chats[position]
        holder.messageTv.text = chat.message
    }


    //for multipleview
    override fun getItemViewType(position: Int): Int {
        return if (chats[position].sender == currentUserID) {
            return SENDER
        } else {
            return RECEIVER
        }


    }
}