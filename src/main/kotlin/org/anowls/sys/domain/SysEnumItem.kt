package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Column
import javax.persistence.Table

/**
 * <p>Title: sys-platform</p>
 * <p>Description: 数据字典分类数据项</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@ApiModel(value = "数据字典分类数据项")
@Table(name = "sys_enum_item")
open class SysEnumItem(
        @ApiModelProperty(value = "数据字典数据项Id", required = true)
        @Column(name = "se_id")
        var seId: String = "",

        @ApiModelProperty(value = "数据字典数据项父Id")
        @Column(name = "sei_parent_id")
        var seiParentId: String = "",

        @ApiModelProperty(value = "数据字典数据项父编码")
        @Column(name = "sei_parent_code")
        var seiParentCode: String = "",

        @ApiModelProperty(value = "编码", required = true)
        @NotBlank(message = "数据字典数据项编码必填！")
        @Length(max = 50, message = "数据字典数据项编码长度不能超过50位！")
        @Column(name = "sei_code")
        var seiCode: String = "",

        @ApiModelProperty(value = "名称", required = true)
        @NotBlank(message = "数据字典数据项名称必填！")
        @Length(max = 60, message = "数据字典数据项名称长度不能超过60位！")
        @Column(name = "sei_name")
        var seiName: String = "",

        @ApiModelProperty(value = "描述")
        @Length(max = 150, message = " 数据字典数据项描述长度不能超过150位！")
        @Column(name = "sei_desc")
        var seiDesc: String = "",

        @ApiModelProperty(value = "排序号", required = true)
        @NotBlank(message = "数据字典数据项排序号必填！")
        @Column(name = "sei_sort")
        var seiSort: Int = 1,

        @ApiModelProperty(value = "拼音")
        @Length(max = 100, message = " 数据字典数据项拼音长度不能超过100位！")
        @Column(name = "pinyin")
        var pinYin: String = "",

        @ApiModelProperty(value = "状态 (1 启用 0 停用) 默认1", required = true)
        @Column(name = "status")
        var status: Int = 1
) : BaseDomain()
