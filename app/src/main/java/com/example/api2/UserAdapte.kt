package com.example.api2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private val context: Context,
    private val list: List<Item>
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contact: TextView = itemView.findViewById(R.id.user_contact)
        val nameText: TextView = itemView.findViewById(R.id.text_name)
        val emailText: TextView = itemView.findViewById(R.id.text_email)
//        val passwordText: TextView = itemView.findViewById(R.id.text_password)
        val updateIcon = itemView.findViewById<ImageView>(R.id.update_icon)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlist, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.contact.text = list[position].contact
        holder.nameText.text=list[position].name
        holder.emailText.text = list[position].email
        holder.updateIcon.setOnClickListener {
            val intent = Intent(context,Update_data::class.java)
            intent.putExtra("customer_Id",list[position].id)
            context.startActivity(intent)
        }

    }
}

