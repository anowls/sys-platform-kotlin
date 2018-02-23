package org.anowls.sys.domain.view

import io.swagger.annotations.ApiModel
import org.anowls.sys.domain.SysEnum

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 数据字典分类-视图对象</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@ApiModel(value = "数据字典分类-视图对象")
data class SysEnumVO(var creatorName : String = "") : SysEnum()
