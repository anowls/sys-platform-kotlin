package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.SysEnumItem
import org.anowls.sys.domain.view.SysEnumItemVO
import org.apache.ibatis.annotations.Mapper

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 数据字典数据项-数据访问</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@Mapper
interface SysEnumItemMapper : BaseMapper<SysEnumItem> {

    /**
     *  根据数据字典代码集合查询数据字典数据项
     *
     *  @param codes 数据字典代码集合
     *  @return 数据字典数据项列表
     */
    fun queryByCodes(codes: List<String>) : List<SysEnumItemVO>

}