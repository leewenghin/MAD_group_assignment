package com.example.project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class SubmissionApprovalActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")

//    class getMark {
//        companion object {
//            var proposal:Long?=null
//            var final:Long?=null
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submission_approval)

        // Declare variable for the data that get from previous activity
        val submissionId = intent.getStringExtra("submissionId")
        val studentName = intent.getStringExtra("studentName")
        val studentId = intent.getStringExtra("studentId")
        val subDate = intent.getStringExtra("subDate")
        val title = intent.getStringExtra("title")
        val abstract = intent.getStringExtra("abstract")
        val UserId = intent.getStringExtra("UserId")

        // Declare variable for view
        val IconTitle = findViewById<ImageView>(R.id.icon_title)
        val UserName = findViewById<TextView>(R.id.user_name)
        val StudentId = findViewById<TextView>(R.id.student_id)
        val Title = findViewById<TextView>(R.id.title)
        val SubmissionDate = findViewById<TextView>(R.id.submission_date)
        val Abstract = findViewById<TextView>(R.id.input_abstract)
        val Feedback = findViewById<TextView>(R.id.input_feedback)
        val ButtonApprove = findViewById<Button>(R.id.btn_approve)
        val ButtonReject = findViewById<Button>(R.id.btn_reject)

        // Declare data for database variable
        val db = FirebaseFirestore.getInstance()

        UserName.text = studentName
        StudentId.text = studentId
        Title.text = title
        SubmissionDate.text = subDate
        Abstract.text = abstract

//        val submissionReference = db.collection("submission")
//
//        // Get student's submission detail query
//        // Get to specific document with id inside submission collection
//        submissionReference.document(submissionId.toString()).get()
//            .addOnSuccessListener { submissionSnapshot ->
//                // If the specific document exist
//                if (submissionSnapshot.exists()) {
//                    submissionSnapshot.reference.collection("users").document(userId.toString())
//                        .get()
//                        .addOnSuccessListener { userSnapshot ->
//                            if (userSnapshot.exists()) {
//                                val lastName = userSnapshot.getString("last_name")
//                                val firstName = userSnapshot.getString("first_name")
//                                val studentId = userSnapshot.getString("std_id")
//                                val title = userSnapshot.getString("title")
//                                val submissionDate = userSnapshot.getString("submission_date")
//                                val abstract = userSnapshot.getString("abstract")
//
//                                UserName.text = "$firstName $lastName"
//                                StudentId.text = "$studentId"
//                                Title.text = "$title"
//                                SubmissionDate.text = "$submissionDate"
//                                Abstract.text = "$abstract"
//                            }
//                        }
//                }
//            }

        ButtonApprove.setOnClickListener {
            val feedback = Feedback.text.toString().trim()

            val submissionStatus = "Approved"

            val data = mapOf(
                "feedback" to feedback,
                "submission_status" to submissionStatus
            )

            val submissionReference2 = db.collection("submission").document(submissionId.toString())

            submissionReference2.collection("users").document(UserId.toString())
                .update(data).addOnSuccessListener{ documentSnapshot ->
                    // The data was successfully saved
                    Toast.makeText(baseContext, "Student work has been approve",
                        Toast.LENGTH_LONG).show()

                    val intent = Intent(this, SubmissionApprovalDetailActivity::class.java)
                    intent.putExtra("studentName", studentName)
                    intent.putExtra("studentId", studentId)
                    intent.putExtra("title", title)
                    intent.putExtra("submissionStatus", submissionStatus)
                    intent.putExtra("subDate", subDate)
                    intent.putExtra("abstract", abstract)
                    startActivity(intent)

                }
                .addOnFailureListener { e ->
                    // The save failed
                    Toast.makeText(baseContext, "Fail to approve, please try again",
                        Toast.LENGTH_LONG).show()
                }
        }

        ButtonReject.setOnClickListener {
            val feedback = Feedback.text.toString().trim()

            val submissionStatus = "Rejected"

            val data = mapOf(
                "feedback" to feedback,
                "submission_status" to submissionStatus
            )

            val submissionReference2 = db.collection("submission").document(submissionId.toString())

            submissionReference2.collection("users").document(UserId.toString())
                .update(data).addOnSuccessListener{ documentSnapshot ->
                    // The data was successfully saved
                    Toast.makeText(baseContext, "Student work has been rejected",
                        Toast.LENGTH_LONG).show()

                    val intent = Intent(this, SubmissionApprovalDetailActivity::class.java)
                    intent.putExtra("studentName", studentName)
                    intent.putExtra("studentId", studentId)
                    intent.putExtra("title", title)
                    intent.putExtra("submissionStatus", submissionStatus)
                    intent.putExtra("subDate", subDate)
                    intent.putExtra("abstract", abstract)
                    startActivity(intent)

                }
                .addOnFailureListener { e ->
                    // The save failed
                    Toast.makeText(baseContext, "Fail to reject, please try again",
                        Toast.LENGTH_LONG).show()
                }
        }
    }
}