package com.tanxin.basex.modules.login.view

import com.tanxin.basex.common.base.mvp.BaseMvpView

interface LoginView : BaseMvpView {
    fun loginSuccess(str:String)
}