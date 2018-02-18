package org.anowls.sys.common.enumeration

import org.anowls.sys.common.IEnum

enum class MessageLevel(private val key : String, private val value : String)  : IEnum<String, String> {

    INFO("info", "普通消息"),
    WARN("warn", "警告消息"),
    FAIL("fail", "失败消息");

    override fun getKey() : String{
        return this.key
    }

    override fun getValue() : String {
        return this.value
    }
}