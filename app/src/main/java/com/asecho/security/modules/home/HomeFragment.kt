package com.asecho.security.modules.home

import android.os.Bundle
import com.asecho.security.R
import com.asecho.security.common.base.BaseFragment
import com.asecho.security.common.utils.Utils
import com.blankj.utilcode.util.BarUtils
import kotlinx.android.synthetic.main.fragment_blank.*


class HomeFragment : BaseFragment() {

    companion object{
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment: HomeFragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun getContentViewId(): Int = R.layout.fragment_blank

    override fun initView() {
        BarUtils.setStatusBarColor(fakeStatusBar, Utils.getThemeColor())
        titleView.setTitle("首页")
    }

}
