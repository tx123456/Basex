package com.tanxin.basex.modules.login.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.tanxin.basex.R
import com.tanxin.basex.common.base.BaseTitleMvpActivity
import com.tanxin.basex.common.base.mvp.CreatePresenter
import com.tanxin.basex.modules.login.persenter.LoginPersenter
import com.tanxin.basex.modules.login.view.LoginView
import org.jetbrains.anko.toast

@CreatePresenter(presenter = [(LoginPersenter::class)])
class LoginActivity : BaseTitleMvpActivity<LoginPersenter>(), LoginView {

    var key: String = ""

    companion object {
        fun startActivity(context: Context, key: String) {
            context.startActivity(Intent(context, LoginActivity::class.java).putExtra("key", key))
        }
    }

    override fun bindLayout(): Int = R.layout.activity_login

    override fun getBundleExtras(extras: Bundle) {
        super.getBundleExtras(extras)
        key = extras.getString("key","")
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        getPresenter().login(key)
    }

    override fun loginSuccess(string: String) {
        toast(string)
    }
}
