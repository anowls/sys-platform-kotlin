package org.anowls.sys.common

import com.github.pagehelper.PageInfo
import org.apache.commons.collections4.CollectionUtils

data class SimplePage<out T>(val totalPages : Int,  val totalElements : Long,  val number : Int,
                             val size : Int, val content : List<T>  = ArrayList(), val hasContent : Boolean,  val isFirst : Boolean, val isLast : Boolean) {

    companion object {
        fun <T> convert(page : PageInfo<T>) : SimplePage<T> {
            return SimplePage<T>(
                    page.pages,
                    page.total,
                    page.pageNum,
                    page.size,
                    page.list,
                    CollectionUtils.isNotEmpty(page.list),
                    page.isIsFirstPage,
                    page.isIsLastPage
            )
        }
    }
}
