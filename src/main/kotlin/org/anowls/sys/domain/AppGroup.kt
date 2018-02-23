package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Column
import javax.persistence.Table

@ApiModel(description = "应用组")
@Table(name = "sys_app_group")
class AppGroup(

        @NotBlank(message = "应用组编码必填！")
        @Length(max = 10, message = "应用组编码长度不能超过10位！")
        @ApiModelProperty(value = "编码", required = true)
        @Column(name = "ag_code")
        var agCode: String = "",

        @NotBlank(message = "应用组名称必填！")
        @Length(max = 60, message = "应用组名称长度不能超过60位！")
        @ApiModelProperty(value = "名称", required = true)
        @Column(name = "ag_name")
        var agName: String = "",

        @Length(max = 150, message = "应用组描述长度不能超过150位！")
        @ApiModelProperty(value = "描述")
        @Column(name = "ag_desc")
        var agDesc: String? = null,

        @NotBlank(message = "应用组排序号必填！")
        @ApiModelProperty(value = "排序号", required = true)
        @Column(name = "sort_number")
        var agSort: Int = 1,

        @ApiModelProperty(value = "状态")
        @Column(name = "status")
        var status: Int = 1
) : BaseDomain()