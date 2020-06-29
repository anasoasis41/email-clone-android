package com.example.emailcloneapp.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.emailcloneapp.R
import com.example.emailcloneapp.ui.home.HomeItemListener
import com.example.emailcloneapp.ui.home.data.EmailData

class UsersAdapter (val context: Context,
                    val usersList: List<EmailData>,
                    val itemUserListener: HomeItemListener
):
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>()
{
    override fun getItemCount() = usersList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layoutId = R.layout.user_list_item
        val view = inflater.inflate(layoutId, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = usersList[position]
        with(holder) {
            userNameText?.let {
                it.text = user.username
                it.contentDescription = user.username
            }

            Glide.with(context)
                .load(user.image)
                .circleCrop()
                .into(userImage)
            holder.itemView.setOnClickListener {
                itemUserListener.onUserItemClick(user)
            }
        }
    }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userNameText = itemView.findViewById<TextView>(R.id.name_user_item_users)
        val userImage = itemView.findViewById<ImageView>(R.id.userImage_item_users)
    }
}