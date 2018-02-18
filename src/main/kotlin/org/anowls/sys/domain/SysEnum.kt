package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.Column
import javax.persistence.Table

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 这里填写描述信息</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@Table(name = "sys_enum")
@ApiModel(value = "数据字典分类")
open class SysEnum : BaseDomain() {

    @ApiModelProperty(name = "数据字典编码")
    @Column(name = "se_ucode", length = 50)
    var seUcode : String = ""

    @ApiModelProperty(name = "数据字典代码")
    @Column(name = "se_code", length = 50)
    var seCode : String = ""

    @ApiModelProperty(name = "数据字典名称")
    @Column(name = "se_name", length = 60)
    var seName : String = ""

    @ApiModelProperty(name = "数据字典描述")
    @Column(name = "se_desc", length = 150)
    var seDesc : String = ""

    @ApiModelProperty(name = "类别: 1:标准代码集, 2:扩展代码集, 3:标准数据集, 4:扩展数据集")
    @Column(name = "se_level")
    var seLevel : Byte = 0

}
