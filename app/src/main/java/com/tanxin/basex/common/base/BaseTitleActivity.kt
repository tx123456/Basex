package com.tanxin.basex.common.base

import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.BarUtils
import com.qmuiteam.qmui.kotlin.onClick
import com.tanxin.basex.R
import kotlinx.android.synthetic.main.base_title_activity.*

/**
 * Created by tanxin on 2018/9/28.
 */
abstract class BaseTitleActivity : BaseActivity() {

    final override fun setBaseView() {
        setContentView(LayoutInflater.from(this).inflate(R.layout.base_title_activity, null))

        activityContainer.addView(LayoutInflater.from(this).inflate(bindLayout(), activityContainer, false))

        BarUtils.setStatusBarColor(this, ContextCompat.getColor(getThisContext(), R.color.app_color_blue))
        BarUtils.addMarginTopEqualStatusBarHeight(titleView)
        titleView.addLeftBackImageButton().onClick {
            onBackPressedSupport()
        }
    }
}