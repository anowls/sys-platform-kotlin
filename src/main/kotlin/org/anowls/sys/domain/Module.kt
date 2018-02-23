package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Column
import javax.persistence.Table

@ApiModel(description = "功能模块")
@Table(name = "sys_module")
open class Module(

        @ApiModelProperty(value = "应用id", required = true)
        @Column(name = "app_id")
        var appId: String = "",

        @NotBlank(message = "模块编码必填！")
        @Length(max = 100, message = "模块编码长度不能超过100位！")
        @ApiModelProperty(value = "编码", required = true)
        @Column(name = "sm_code")
        var smCode: String = "",

        @NotBlank(message = "模块名称必填！")
        @Length(max = 100, message = "模块名称长度不能超过100位！")
        @ApiModelProperty(value = "名称", required = true)
        @Column(name = "sm_name")
        var smName: String = "",

        @Length(max = 150, message = "模块描述长度不能超过150位！")
        @ApiModelProperty(value = "描述")
        @Column(name = "sm_desc")
        var smDesc: String? = null,

        @ApiModelProperty(value = "状态 (1 启用 0 停用) 默认 1", required = true)
        @Column(name = "status")
        var status: Int = 1,

        @ApiModelProperty(value = "排序号")
        @Column(name = "sort_number")
        var sortNumber: Int = 1

) : BaseDomain()