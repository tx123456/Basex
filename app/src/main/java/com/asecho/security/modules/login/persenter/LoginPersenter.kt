package com.asecho.security.modules.login.persenter

import com.asecho.base.common.base.mvp.BasePresenter
import com.asecho.security.modules.login.view.LoginView

/**
 * @author TANXIN
 * @date 2018/8/1
 */


class LoginPersenter : BasePresenter<LoginView>() {
    fun login(str:String) {
        mView?.loginSuccess(str)
    }
}
