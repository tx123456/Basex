package com.asecho.security.common.base

import android.os.Bundle
import android.view.View

import com.blankj.utilcode.util.ToastUtils
import com.asecho.base.common.base.mvp.BasePresenter
import com.asecho.security.common.base.mvp.BaseMvpView
import com.asecho.security.common.base.mvp.PresenterDispatch
import com.asecho.security.common.base.mvp.PresenterProviders

/**
 * @author TANXIN
 * @date 2018/8/1
 */


abstract class BaseMvpFragment<P : BasePresenter<*>> : BaseFragment(), BaseMvpView {
    private lateinit var presenterProviders: PresenterProviders
    private lateinit var mPresenterDispatch: PresenterDispatch

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenterProviders = PresenterProviders.inject(this)
        mPresenterDispatch = PresenterDispatch(presenterProviders)

        mPresenterDispatch.attachView<BasePresenter<*>>(activity, this)
        mPresenterDispatch.onCreatePresenter<BasePresenter<*>>(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mPresenterDispatch.onSaveInstanceState<BasePresenter<*>>(outState)
    }

    protected fun getPresenter(): P = presenterProviders.getPresenter(0)


    override fun onDestroyView() {
        super.onDestroyView()
        mPresenterDispatch.detachView<BasePresenter<*>>()
    }

    override fun showError(error: String) {
        ToastUtils.showShort(error)
    }
}
