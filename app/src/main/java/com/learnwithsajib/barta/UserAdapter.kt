package com.learnwithsajib.barta

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learnwithsajib.barta.ModelClass.User
import com.learnwithsajib.barta.databinding.ItemUserListBinding

class UserAdapter(var user:UserAdapter.UserListener)
    :ListAdapter<User,UserAdapter.UserViewHolder>(comparator) {


    //interface create to pass data  to OnlineFragment

    interface UserListener{
        fun moveUser(user:User)

    }




    class UserViewHolder(var binding: ItemUserListBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
    return UserViewHolder(ItemUserListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
      getItem(position).let {

          holder.binding.usernameid.text=it.name
          holder.binding.usercontactid.text=it.contact

          holder.itemView.setOnClickListener {_ ->
user.moveUser(it)


          }
      }
    }


    companion object
    {
        var comparator=object :DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
               return oldItem.contact==newItem.contact
            }



            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
              return oldItem==newItem
            }

        }
    }
}