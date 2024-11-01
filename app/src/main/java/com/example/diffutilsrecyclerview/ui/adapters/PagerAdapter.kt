package com.example.diffutilsrecyclerview.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.paging.ExperimentalPagingApi
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.diffutilsrecyclerview.ui.fragment.ExploreFragment
import com.example.diffutilsrecyclerview.ui.fragment.RecipesFragment
import com.example.diffutilsrecyclerview.ui.fragment.UsersFragment
import javax.inject.Inject

@ExperimentalPagingApi
class PagerAdapter @Inject constructor(activity: FragmentActivity) : FragmentStateAdapter(activity) {

//    @Inject lateinit var recipesFragment: RecipesFragment
//    @Inject lateinit var usersFragment: UsersFragment

    private val fragments = listOf(RecipesFragment(), UsersFragment(), ExploreFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}