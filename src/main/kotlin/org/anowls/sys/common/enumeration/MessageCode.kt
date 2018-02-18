package org.anowls.sys.common.enumeration

import org.anowls.sys.common.IEnum

enum class MessageCode(private val key : Int, private val value : String) : IEnum<Int, String> {

    SUCCESS(200, "操作成功"),
    SERVER_ERROR(500, "内部错误"),
    NOT_FOUND(404, "未知的请求"),
    BAD_REQUEST(400, "错误的请求"),
    FORBIDDEN(403, "认证错误");

    override fun getKey() : Int{
        return this.key
    }

    override fun  getValue() : String {
        return this.value
    }
}