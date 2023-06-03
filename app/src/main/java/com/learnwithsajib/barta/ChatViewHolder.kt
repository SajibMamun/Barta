package com.learnwithsajib.barta

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatViewHolder (var itemView: View):RecyclerView.
ViewHolder(itemView) {

      var  messageTv:TextView = itemView.findViewById(R.id.messageTv)
}