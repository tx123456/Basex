package com.asecho.security.modules.login.view

import com.asecho.security.common.base.mvp.BaseMvpView

interface LoginView : BaseMvpView {
    fun loginSuccess(str:String)
}