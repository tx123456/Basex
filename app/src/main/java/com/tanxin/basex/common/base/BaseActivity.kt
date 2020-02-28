package com.tanxin.basex.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.blankj.utilcode.util.LogUtils
import me.yokeyword.fragmentation.SupportActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by tanxin on 2018/9/28.
 * BaseActivity
 */
abstract class BaseActivity : SupportActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindow()
        val extras = intent.extras
        extras?.let {
            getBundleExtras(extras)
        }
        setBaseView()
        LogUtils.i(javaClass.name)

        EventBus.getDefault().register(this)
        initView(savedInstanceState)
    }

    open fun setWindow(){
    }

    open fun setBaseView(){
        setContentView(LayoutInflater.from(this).inflate(bindLayout(), null))
    }

    /**
     * 获取布局资源
     * @return 布局资源
     */
    protected abstract fun bindLayout(): Int


    /**
     * EventBus接收消息
     * @param event 获取事件总线信息
     */
    open fun onEventComing(event: BaseEvent<*>){}

    /**
     * Bundle  传递数据
     *
     * @param extras Bundle
     */
    open fun getBundleExtras(extras: Bundle){}

    /**
     * 初始化界面
     * @param savedInstanceState savedInstanceState
     */
    protected abstract fun initView(savedInstanceState: Bundle?)

    /**
     * EventBus接收消息
     *
     * @param event 消息接收
     */
    @Subscribe
    fun onEventMainThread(event: BaseEvent<*>?) {
        if (null != event) {
            onEventComing(event)
        }
    }

    fun getThisContext(): Context = this

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}