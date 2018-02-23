package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Column
import javax.persistence.Table

@ApiModel(value = "菜单")
@Table(name = "sys_menu")
open class Menu(

        @ApiModelProperty(value = "应用id", required = true)
        @Column(name = "app_id")
        var appId: String = "",

        @NotBlank(message = "菜单编码必填！")
        @Length(max = 100, message = "菜单编码长度不能超过100位！")
        @ApiModelProperty(value = "编码", required = true)
        @Column(name = "sm_code")
        var smCode: String = "",

        @NotBlank(message = "菜单名称必填！")
        @Length(max = 150, message = "菜单名称长度不能超过150位！")
        @ApiModelProperty(value = "名称", required = true)
        @Column(name = "sm_name")
        var smName: String = "",

        @ApiModelProperty(value = "上级菜单")
        @Column(name = "sm_parent_id")
        var smParentId: String? = null,

        @NotBlank(message = "菜单层级必填！")
        @ApiModelProperty(value = "层级", required = true)
        @Column(name = "sm_level")
        var smLevel: Int = 1,

        @ApiModelProperty(value = "功能项id")
        @Column(name = "sf_id")
        var sfId: String? = null,

        @NotBlank(message = "菜单排序号必填！")
        @ApiModelProperty(value = "排序号", required = true)
        @Column(name = "sort_number")
        var sortNumber: Int = 1,

        @Length(max = 150, message = "菜单图标长度不能超过150位！")
        @ApiModelProperty(value = "图标")
        @Column(name = "sm_icon")
        var smIcon: String = "",

        @Length(max = 150, message = "菜单标题长度不能超过150位！")
        @ApiModelProperty(value = "标题", required = true)
        @Column(name = "sm_caption")
        var smCaption: String? = null,

        @ApiModelProperty(value = "打开方式（0 不处理 1 vue 2 iframe） 默认1", required = true)
        @Column(name = "open_type")
        var openType: Int = 1,

        @Length(max = 500, message = "菜单打开URL长度不能超过500位！")
        @ApiModelProperty(value = "打开URL")
        @Column(name = "open_url")
        var openUrl: String? = null,

        @ApiModelProperty(value = "状态 （1 启用 0 停用） 默认1", required = true)
        @Column(name = "status")
        var status: Int = 1,

        @ApiModelProperty(value = "菜单编码")
        @Column(name = "menu_code")
        var menuCode: String = "",

        @ApiModelProperty(value = "菜单级联编码")
        @Column(name = "menu_code_path")
        var menuCodePath: String = "",

        @ApiModelProperty(value = "运维组件名称")
        @Column(name = "component")
        var component: String? = null

) : BaseDomain()