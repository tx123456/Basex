package com.tanxin.base.common.base.mvp

import android.content.Context
import android.os.Bundle

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @author TANXIN
 * @date 2018/7/31
 */


open class BasePresenter<V> {
    private var compositeDisposable: CompositeDisposable? = null
    protected var mContext: Context? = null
    protected var mView: V? = null

    fun onCleared() {
    }

    fun attachView(context: Context, view: V) {
        this.mContext = context
        this.mView = view
    }

    fun detachView() {
        dispose()
        this.mView = null
    }

    fun isAttachView(): Boolean = this.mView != null

    fun onCreatePresenter(savedState: Bundle?) {

    }

    fun onDestroyPresenter() {
        this.mContext = null
        detachView()
    }

    fun onSaveInstanceState(outState: Bundle) {

    }


    fun addDisposable(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable)
    }

    private fun dispose() {
        compositeDisposable?.dispose()
    }

}
