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
import com.example.emailcloneapp.ui.home.data.EmailData
import java.util.ArrayList

class SearchAdapter (val context: Context,
                     val usersList: ArrayList<EmailData>): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>()
{
    override fun getItemCount() = usersList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layoutId = R.layout.item_search_list
        val view = inflater.inflate(layoutId, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val user = usersList[position]
        with(holder) {
            nameText?.let {
                it.text = user.username
                it.contentDescription = user.username
            }

            emailText.text = user.email

            Glide.with(context)
                .load(user.image)
                .circleCrop()
                .into(userImage)
        }
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage = itemView.findViewById<ImageView>(R.id.userImage)
        val nameText = itemView.findViewById<TextView>(R.id.name_textview)
        val emailText = itemView.findViewById<TextView>(R.id.email_textview)
    }
}