package com.tanxin.basex.modules.login.persenter

import com.tanxin.base.common.base.mvp.BasePresenter
import com.tanxin.basex.modules.login.view.LoginView

/**
 * @author TANXIN
 * @date 2018/8/1
 */


class LoginPersenter : BasePresenter<LoginView>() {
    fun login(str:String) {
        mView?.loginSuccess(str)
    }
}
