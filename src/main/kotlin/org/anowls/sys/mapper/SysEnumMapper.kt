package org.anowls.sys.mapper

import org.anowls.sys.common.BaseMapper
import org.anowls.sys.domain.SysEnum
import org.anowls.sys.domain.view.SysEnumItemVO
import org.anowls.sys.domain.view.SysEnumVO
import org.apache.ibatis.annotations.Mapper

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 这里填写描述信息</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@Mapper
interface SysEnumMapper : BaseMapper<SysEnum> {

    fun findAll() : List<SysEnumVO>

}