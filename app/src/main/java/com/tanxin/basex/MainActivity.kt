package com.tanxin.basex

import android.os.Bundle
import com.qmuiteam.qmui.kotlin.onClick
import com.tanxin.basex.common.base.BaseActivity
import com.tanxin.basex.modules.login.activity.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun bindLayout(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        tvName.onClick {
            LoginActivity.startActivity(getThisContext(),"loin")
        }
    }
}
