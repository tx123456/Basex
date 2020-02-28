package com.asecho.security.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.jessyan.autosize.AutoSizeConfig

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

import me.yokeyword.fragmentation.SupportFragment
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator


/**
 * @author TANXIN
 * @date 2018/6/20/020
 */


abstract class BaseFragment : SupportFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        EventBus.getDefault().register(this)
        arguments?.let {
            getBundleExtras(arguments)
        }
        val rootView = inflater.inflate(getContentViewId(), container, false)
        noLazy()
        return rootView
    }


    fun getThisContext(): Context = _mActivity

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        AutoSizeConfig.getInstance().isCustomFragment = true
        initView()
    }

    //不使用懒加载
    fun noLazy() {

    }

    protected  abstract fun getContentViewId() :Int

    /**
     * Bundle  传递数据
     *
     * @param extras Bundle
     */
    open fun getBundleExtras(extras: Bundle?){}

    /**
     * EventBus接收消息
     *
     * @param event 获取事件总线信息
     */
    open fun onEventComing(event: BaseEvent<*>){}

    /**
     * EventBus接收消息
     *
     * @param event 消息接收
     */
    @Subscribe
    fun onEventMainThread(event: BaseEvent<*>?) {
        event?.let {
            onEventComing(event)
        }
    }

    protected abstract fun initView()


    //设置动画
    override fun onCreateFragmentAnimator(): FragmentAnimator {
        // 设置默认Fragment动画  默认竖向(和安卓5.0以上的动画相同)
        //        return super.onCreateFragmentAnimator();
        // 设置横向(和安卓4.x动画相同)
        return DefaultHorizontalAnimator()
        // 设置自定义动画
        //        return new FragmentAnimator(enter,exit,popEnter,popExit);
    }


    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }
}
