package com.tanxin.basex.common.utils

import android.annotation.SuppressLint
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

class Utils {
  companion object {

    /**
     * 获取进程号对应的进程名
     * @param pid 进程号
     * @return 进程名
     */
    fun getProcessName(pid:Int):String?{
      var  reader: BufferedReader?=null
      var fileReader: FileReader?=null

      try {
        fileReader = FileReader("/proc/$pid/cmdline")
        reader = BufferedReader(fileReader)
        var processName:String = reader.readLine()
        processName = processName.trim()
        return processName
      }catch (e:Exception){
        e.printStackTrace()
      }finally {
        try {
          reader?.close()
          fileReader?.close()
        }catch (e: IOException){
          e.printStackTrace()
        }
      }
      return null
    }

    @SuppressLint("RestrictedApi")
    fun disableShiftMode(view: BottomNavigationView){
      val menuView = view.getChildAt(0) as BottomNavigationMenuView

      try {
        val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        for (i in 0 until menuView.childCount){
          val item = menuView.getChildAt(i) as BottomNavigationItemView
          item.setShifting(false)
          item.setChecked(item.itemData.isChecked)
        }
      }catch (e:NoSuchFieldException){
        Log.e("BNVHelper", "Unable to get shift mode field", e)
      }catch (e: IllegalAccessException){
        Log.e("BNVHelper", "Unable to change value of shift mode", e)
      }
    }

  }
}