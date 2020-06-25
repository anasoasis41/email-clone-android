package com.example.emailcloneapp.ui.home

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

class EmailsAdapter (val context: Context,
                     val emailsList: List<EmailData>,
                     val itemListener: EmailItemListener):
    RecyclerView.Adapter<EmailsAdapter.ViewHolder>()
{
    override fun getItemCount() = emailsList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layoutId = R.layout.emails_list_item
        val view = inflater.inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val email = emailsList[position]
        with(holder) {
            titleText?.let {
                it.text = email.title
                it.contentDescription = email.title
            }
            subTitleText?.let {
                it.text = email.subtitle
                it.contentDescription = email.subtitle
            }

            Glide.with(context)
                .load(email.image)
                .into(userImage)
            holder.itemView.setOnClickListener {
                itemListener.onEmailItemClick(email)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText = itemView.findViewById<TextView>(R.id.title_textview)
        val subTitleText = itemView.findViewById<TextView>(R.id.subTitle_textview)
        val userImage = itemView.findViewById<ImageView>(R.id.userImage)
    }

    interface EmailItemListener {
        fun onEmailItemClick(email: EmailData)
    }
}