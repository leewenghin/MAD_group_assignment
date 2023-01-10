package com.example.project

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity2 : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private val db = FirebaseFirestore.getInstance()

    private lateinit var userId: String

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    var getDeadline = ""

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        userId= FirebaseAuth.getInstance().currentUser!!.uid

        val btn_timePicker = findViewById<Button>(R.id.btn_timePicker)

        val te_label = findViewById<TextInputEditText>(R.id.te_label)
        val te_batch = findViewById<TextInputEditText>(R.id.te_batch)
        val btn_addSubmission = findViewById<Button>(R.id.btn_addSubmission)

        pickDate(btn_timePicker)

        btn_addSubmission.setOnClickListener {
            val te_Label = te_label.text.toString().trim()
            val te_Batch = te_batch.text.toString().trim()

//            val deadline = SimpleDateFormat("dd-MM-yyyy HH:mm").parse(getDeadline)
            val deadline = getDeadline

            // Current Date Time format
            val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")

            // Current Date Time
            val currentDateTime = Calendar.getInstance().time

            // Convert current date time from (Date to String)
            val dateCreated = dateFormat.format(currentDateTime)


            val newDocument = db.collection("submission").document()

            val data = mapOf(
                "label" to te_Label,
                "batch" to te_Batch,
                "deadline" to deadline,
                "date_created" to dateCreated,
                "submission_id" to newDocument.id,
                "status" to ""
            )

            newDocument.set(data)
                .addOnSuccessListener { documentReference ->
                    // The data was successfully saved
                    Toast.makeText(baseContext, "Submission has been add successfully",
                        Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e ->
                    // The save failed
                    Toast.makeText(baseContext, "Fail to add submission, please try again",
                        Toast.LENGTH_LONG).show()
                }
        }
    }

//    private fun getCurrentDateTime(){
//
//
//        val calendar = Calendar.getInstance()
//        val currentYear = calendar.get(Calendar.YEAR)
//        val currentMonth = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH starts from 0
//        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
//        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
//        val currentMinute = calendar.get(Calendar.MINUTE)
//        val currentSecond = calendar.get(Calendar.SECOND)
//        currentDateTime = "$currentDay-$currentMonth-$currentYear $currentHour:$currentMinute:$currentSecond"
//    }

    private fun getDateTimeCalender(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun pickDate(btn_timePicker:Button){
        btn_timePicker.setOnClickListener{
            getDateTimeCalender()
            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayofMonth: Int) {
        savedDay = dayofMonth
        savedMonth = month + 1
        savedYear = year

        getDateTimeCalender()
        TimePickerDialog(this, this, hour, minute, true).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val tv_textTime = findViewById<TextView>(R.id.tv_textTime)

        savedHour = hourOfDay
        savedMinute = minute

        getDeadline = String.format("%02d", savedDay) + "-" + String.format("%02d", savedMonth) + "-" + savedYear +
                " " + String.format("%02d", savedHour) + ":" + String.format("%02d", savedMinute)

        tv_textTime.text = "${String.format("%02d", savedDay)}/${String.format("%02d", savedMonth)}/" +
                "$savedYear ${String.format("%02d", savedHour)}:" + "${String.format("%02d", savedMinute)}"
    }
}