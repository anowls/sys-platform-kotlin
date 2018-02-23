package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.SysEnum
import org.anowls.sys.domain.view.SysEnumVO
import org.apache.ibatis.annotations.Mapper

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 数据字典-数据访问</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@Mapper
interface SysEnumMapper : BaseMapper<SysEnum> {

    /**
     *  根据查询条件查询数据字典
     *
     *  @param filter 查询条件
     * @return 数据字典列表
     */
    fun findAll(filter: Map<String, Any?>?): List<SysEnumVO>

}