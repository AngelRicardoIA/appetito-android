package com.angel.appetito.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.angel.appetito.BebidasFragment
import com.angel.appetito.ComidaFragment
import com.angel.appetito.ComplementosFragment

class MenuPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ComidaFragment()
            1 -> BebidasFragment()
            2 -> ComplementosFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}