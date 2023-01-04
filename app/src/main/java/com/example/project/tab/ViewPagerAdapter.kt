package com.example.project.tab

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.project.fragment.HomeFragment

class ViewPagerAdapter(fragmentActivity: HomeFragment) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){ // position show fragment
            0 -> {PendingFragment() } // When index = 0, return PendingFragment
            1 -> {CompletedFragment() }
            else -> {throw Resources.NotFoundException("Position Not Found")}
//            else -> {return PendingFragment()}
            //The ResourceNotFoundException exception is thrown if a security provider looks for a resource that should exist, but is unable to find that resource
        }
    }

}