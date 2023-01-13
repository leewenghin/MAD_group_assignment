package com.example.project.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.*
import com.google.firebase.firestore.FirebaseFirestore

class ViewGradeAdapter(private var viewGradeList: ArrayList<Grade>) :
    RecyclerView.Adapter<ViewGradeAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentName: TextView = itemView.findViewById(R.id.student_name3)
        val scoreBar: TextView = itemView.findViewById(R.id.scorebar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.grade_item_list, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val db = FirebaseFirestore.getInstance()
        val userId = viewGradeList[position].user_id

        holder.scoreBar.text = viewGradeList[position].total_mark.toString()

        db.collection("users").document(userId.toString()).get()
            .addOnSuccessListener { userSnapshot ->
                if (userSnapshot.exists()) {
                    var firstName: String? = null
                    var lastName: String? = null

                    firstName = userSnapshot.getString("first_name")
                    lastName = userSnapshot.getString("last_name")
                    holder.studentName.text = "$firstName $lastName"
                }
            }
    }

    override fun getItemCount(): Int {
        return viewGradeList.size
    }
}