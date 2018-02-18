package org.anowls.sys.common

import com.google.common.base.Splitter
import org.springframework.util.CollectionUtils
import org.springframework.util.StringUtils

data class PageQuery(val page : Int = 0, val size : Int = 10, private val filter : String = "",  private val order : String = "") {

    fun convertFilterToMap() : Map<String, String>? {
        if (StringUtils.isEmpty(this.filter)) {
            return null
        }
        val list : List<String> = Splitter.on("").omitEmptyStrings().trimResults().splitToList(this.filter)

        if (CollectionUtils.isEmpty(list)) {
            return null
        }
        val map : Map<String, String> = HashMap()
        val var3  = list.iterator()

        while (var3.hasNext()) {
            val s : String = var3.next()
            val item : List<String>  = Splitter.on("=").limit(2).omitEmptyStrings().trimResults().splitToList(s)
            if (item.size == 2) {
                map + Pair(item[0],item[1])
            }
        }

        return map
    }

    fun convertSort() : String {
        if (StringUtils.isEmpty(this.order)) {
            return ""
        }
        val list : List<String> = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(this.order)
        if (CollectionUtils.isEmpty(list)) {
            return ""
        }
        val sb = StringBuilder()
        val var3  = list.iterator()

        while (var3.hasNext()) {
            val s : String = var3.next()
            val item : List<String>  = Splitter.on("=").limit(2).omitEmptyStrings().trimResults().splitToList(s)
            if (item.size == 2) {
                val fieldName : String= item[0]
                val sortDirection : String = item[1]
                sb.append(fieldName)
                sb.append(" ")
                sb.append(sortDirection)
                sb.append(",")
            }
        }

        if (StringUtils.isEmpty(sb.toString())) {
            return ""
        }
        return sb.toString().substring(0, sb.toString().length - 1)
    }
}