package com.example.project

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DashboardActivity : AppCompatActivity() {

    private lateinit var nav_status: TabLayout
    private lateinit var submission_list: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        nav_status = findViewById(R.id.nav_status)
        submission_list = findViewById(R.id.submissionlist)
        submission_list.adapter = ViewPagerAdapter(this) // Add submission_list(viewpager) to Adapter
        TabLayoutMediator(nav_status, submission_list){ tab,index ->  // pass 2 argument and get 2 argument
            tab.text = when(index){
                0 -> {"Pending"}
                1 -> {"Completed"}
                else -> {throw Resources.NotFoundException("Position Not Found")}
            }
        }.attach() //Instantiating a TabLayoutMediator will only create the mediator object, attach() will link the TabLayout and the ViewPager2 together
    }
}