package org.anowls.sys.common

interface IEnum<out K, out V> {
    fun getKey() : K

    fun getValue() : V
}