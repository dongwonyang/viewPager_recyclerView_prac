package com.example.mbti_prac

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SurveyViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = mutableListOf<Fragment>()

    // Fragment를 추가하는 메서드
    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    // Fragment를 삭제하는 메서드 (선택적)
    fun removeFragment(fragment: Fragment) {
        fragmentList.remove(fragment)
    }

    // ViewPager의 페이지 개수 반환
    override fun getItemCount(): Int {
        return 4
    }

    // ViewPager의 각 페이지에 해당하는 Fragment 반환
    override fun createFragment(position: Int): Fragment {
        return SurveyViewPagerFrag.newInstance(position)
    }
}
