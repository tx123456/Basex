package com.asecho.security.common.base

/**
 * @author TANXIN
 * @date 2018/6/20/020
 */
data class BaseEvent<T>(var eventCode:Int = -1,var data: T?) {
    constructor(eventCode:Int):this(eventCode,null)
}
