package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Column
import javax.persistence.Table

@ApiModel(value = "功能项")
@Table(name = "sys_feature")
open class Feature(

        @ApiModelProperty(value = "应用id", required = true)
        @Column(name = "app_id")
        var appId: String = "",

        @ApiModelProperty(value = "模块id", required = true)
        @Column(name = "sm_id")
        var smId: String = "",

        @NotBlank(message = "功能编码必填！")
        @Length(max = 100, message = "功能编码长度不能超过100位！")
        @ApiModelProperty(value = "编码", required = true)
        @Column(name = "sf_code")
        var sfCode: String = "",

        @ApiModelProperty(value = "名称", required = true)
        @NotBlank(message = "功能名称必填！")
        @Length(max = 100, message = "功能名称长度不能超过100位！")
        @Column(name = "sf_name")
        var sfName: String = "",

        @ApiModelProperty(value = "描述")
        @Length(max = 150, message = "功能描述长度不能超过150位！")
        @Column(name = "sf_desc")
        var sfDesc: String? = null,

        @ApiModelProperty(value = "控制层名称", required = true)
        @Length(max = 150, message = "控制层名称长度不能超过150位！")
        @Column(name = "controller_name")
        var controllerName: String? = null,

        @ApiModelProperty(value = "Action名称", required = true)
        @Length(max = 150, message = "Action名称长度不能超过150位！")
        @Column(name = "action_name")
        var actionName: String? = null,

        @ApiModelProperty(value = "请求地址", required = true)
        @Length(max = 150, message = "请求地址长度不能超过150位！")
        @Column(name = "request_url")
        var requestUrl: String? = null,

        @ApiModelProperty(value = "是否菜单项 默认 0", required = true)
        @Column(name = "is_menu")
        var isMenu: Int = 0,

        @ApiModelProperty(value = "状态 （1 启用 0 停用） 默认1", required = true)
        @Column(name = "status")
        var status: Int = 1,

        @ApiModelProperty(value = "功能类型 (1 功能 2 小部件)  默认1", required = true)
        @Column(name = "sf_type")
        var sfType: Int = 1
) : BaseDomain()