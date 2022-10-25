package com.maximmesh.nasamaterials.ui.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmActivity: FragmentActivity) : FragmentStateAdapter(fragmActivity) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), SystemFragment())
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}