package com.tanxin.basex.common.base

import android.os.Bundle

import com.blankj.utilcode.util.ToastUtils
import com.tanxin.base.common.base.mvp.BasePresenter
import com.tanxin.basex.common.base.mvp.BaseMvpView
import com.tanxin.basex.common.base.mvp.PresenterDispatch
import com.tanxin.basex.common.base.mvp.PresenterProviders

/**
 * @author TANXIN
 * @date 2018/8/1
 */


abstract class BaseMvpActivity<P : BasePresenter<*>> : BaseActivity(), BaseMvpView {
    private lateinit var  presenterProviders: PresenterProviders

    private lateinit var mPresenterDispatch: PresenterDispatch

    override fun initView(savedInstanceState: Bundle?) {
        presenterProviders = PresenterProviders.inject(this)
        mPresenterDispatch = PresenterDispatch(presenterProviders)

        mPresenterDispatch.attachView<BasePresenter<*>>(this, this)
        mPresenterDispatch.onCreatePresenter<BasePresenter<*>>(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mPresenterDispatch.onSaveInstanceState<BasePresenter<*>>(outState)
    }

    protected fun getPresenter(): P = presenterProviders.getPresenter(0)

    override fun showError(error: String) {
        ToastUtils.showShort(error)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenterDispatch.detachView<BasePresenter<*>>()
    }
}
