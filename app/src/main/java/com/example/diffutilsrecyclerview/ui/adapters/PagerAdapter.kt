package com.example.diffutilsrecyclerview.ui.adapters

import androidx.fragment.app.Fragment
import androidx.paging.ExperimentalPagingApi
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.diffutilsrecyclerview.ui.fragment.ExploreFragment
import com.example.diffutilsrecyclerview.ui.fragment.RecipesFragment
import com.example.diffutilsrecyclerview.ui.fragment.UsersFragment

@ExperimentalPagingApi
class PagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

//    @Inject lateinit var recipesFragment: RecipesFragment
//    @Inject lateinit var usersFragment: UsersFragment
//    private val fragments = listOf(RecipesFragment(), UsersFragment(), ExploreFragment())

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RecipesFragment()
            1 -> UsersFragment()
            else -> ExploreFragment()
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return itemId in 0 until itemCount
    }
}