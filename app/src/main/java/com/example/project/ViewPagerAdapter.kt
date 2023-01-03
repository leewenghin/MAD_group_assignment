package com.example.project

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){ // position show fragment
            0 -> {PendingFragment() }
            1 -> {CompletedFragment() }
            else -> {throw Resources.NotFoundException("Position Not Found")}
            //The ResourceNotFoundException exception is thrown if a security provider looks for a resource that should exist, but is unable to find that resource
        }
    }

}