package com.angel.appetito.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.angel.appetito.ui.BebidasFragment
import com.angel.appetito.ui.ComidaFragment
import com.angel.appetito.ui.ComplementosFragment

class MenuPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val restaurantId: Int
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ComidaFragment.newInstance(restaurantId)
            1 -> BebidasFragment.newInstance(restaurantId)
            2 -> ComplementosFragment.newInstance(restaurantId)
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}