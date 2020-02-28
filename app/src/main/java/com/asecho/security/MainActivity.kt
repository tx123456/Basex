package com.asecho.security

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.asecho.security.common.api.EventCode
import com.asecho.security.common.base.BaseActivity
import com.asecho.security.common.base.BaseEvent
import com.asecho.security.common.base.BaseFragment
import com.asecho.security.modules.home.HomeFragment
import com.asecho.security.modules.my.MyFragment
import com.blankj.utilcode.util.LogUtils
import com.qmuiteam.qmui.kotlin.onClick
import com.tencent.bugly.beta.Beta
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus

class MainActivity : BaseActivity() {

    companion object{
        val Home = 0
        val My = 1
    }

    private val mFragments: Array<BaseFragment?> = arrayOfNulls(2)
    override fun bindLayout(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        val homeFragment: BaseFragment? = findFragment(HomeFragment::class.java)
        if (homeFragment == null) {
            mFragments[Home] = HomeFragment.newInstance()
            mFragments[My] = MyFragment.newInstance()
            loadMultipleRootFragment(
                R.id.mainContainer,
                Home,
                mFragments[Home],
                mFragments[My]
            )
        } else {
            mFragments[Home] = homeFragment
            mFragments[My] = findFragment(MyFragment::class.java)
        }
        rbHome.onClick {
            setTabState(Home)
        }
        rbMy.onClick {
            setTabState(My)
        }
    }

    override fun onBackPressedSupport() {
        val launcherIntent = Intent(Intent.ACTION_MAIN)
        launcherIntent.addCategory(Intent.CATEGORY_HOME)
        startActivity(launcherIntent)
    }
    private fun setTabState(position: Int) {
        showHideFragment(mFragments[position])
        rbHome.setTextColor(ContextCompat.getColor(getThisContext(), if (rbHome.isChecked) R.color.app_color_blue else R.color.text6))
        rbMy.setTextColor(ContextCompat.getColor(getThisContext(), if (rbMy.isChecked) R.color.app_color_blue else R.color.text6)
        )
    }

    override fun onResume() {
        super.onResume()
        getUpdateInfo()
    }

    /**
     * Bugly获取更新信息
     */
    private fun getUpdateInfo() {
        val info = Beta.getUpgradeInfo() ?: return
        LogUtils.i(
            "Bugly更新信息", "title:" + info.title
                    + "\nnewFeature:" + info.newFeature
                    + "\nversionName:" + info.versionName
                    + "\npublishTime:" + info.publishTime
        )
        if (info.versionCode != 0 && info.versionName != null) {
//            GlobalToken.updateNewVersion(true)
            EventBus.getDefault().post(BaseEvent(EventCode.NEW_VERSION, true))
        }
    }

}
