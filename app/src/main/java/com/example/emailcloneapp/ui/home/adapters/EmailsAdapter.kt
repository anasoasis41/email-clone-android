package com.example.emailcloneapp.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daimajia.swipe.SwipeLayout
import com.example.emailcloneapp.R
import com.example.emailcloneapp.ui.home.HomeItemListener
import com.example.emailcloneapp.ui.home.data.EmailData

class EmailsAdapter (val context: Context,
                     val emailsList: List<EmailData>,
                     val itemEmailListener: HomeItemListener
):
    RecyclerView.Adapter<EmailsAdapter.EmailsViewHolder>()
{
    override fun getItemCount() = emailsList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layoutId = R.layout.emails_list_item
        val view = inflater.inflate(layoutId, parent, false)
        return EmailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailsViewHolder, position: Int) {
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
                .circleCrop()
                .into(userImage)
            holder.itemView.setOnClickListener {
                itemEmailListener.onEmailItemClick(email)
            }

            holder.swipeLayout.showMode = SwipeLayout.ShowMode.PullOut
            holder.swipeLayout.addSwipeListener(object : SwipeLayout.SwipeListener {
                override fun onOpen(layout: SwipeLayout?) {

                }

                override fun onUpdate(layout: SwipeLayout?, leftOffset: Int, topOffset: Int) {

                }

                override fun onStartOpen(layout: SwipeLayout?) {

                }

                override fun onStartClose(layout: SwipeLayout?) {

                }

                override fun onHandRelease(layout: SwipeLayout?, xvel: Float, yvel: Float) {

                }

                override fun onClose(layout: SwipeLayout?) {

                }

            })
        }
    }

    inner class EmailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val swipeLayout: SwipeLayout = itemView.findViewById<SwipeLayout>(R.id.swipe)
        val titleText = itemView.findViewById<TextView>(R.id.title_textview)
        val subTitleText = itemView.findViewById<TextView>(R.id.subTitle_textview)
        val userImage = itemView.findViewById<ImageView>(R.id.userImage)
    }
}