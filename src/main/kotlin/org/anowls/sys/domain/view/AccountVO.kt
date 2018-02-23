package org.anowls.sys.domain.view

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.anowls.sys.domain.Account
import org.anowls.sys.domain.Organization
import org.anowls.sys.domain.Role


/**
 * @author YCJ
 * @date 2017-11-03
 * Copyright Hanboard
 * Created by YCJ on 2017-11-03
 * 用户扩展信息类
 */
@ApiModel(description = "用户信息-视图对象")
data class AccountVO(

        @ApiModelProperty(value = "默认机构")
        @Transient
        var organization: Organization? = null,

        @ApiModelProperty(value = "用户角色分类集合")
        var categories: List<SysEnumItemVO> = emptyList(),

        @ApiModelProperty(value = "默认角色")
        var defaultRole: Role? = null,

        @ApiModelProperty(value = "角色集合")
        var roles: List<Role> = emptyList(),

        @ApiModelProperty(value = "应用集合")
        var applications: List<ApplicationVO> = emptyList(),
//
//@ApiModelProperty(value = "部件信息")
//@Transient
//var layouts: List<WidgetLayout>? = null,

        @ApiModelProperty(value = "默认角色id")
        @Transient
        var roleId: String? = null,

        @ApiModelProperty(value = "默认角色分类code")
        var shortCode: String? = null,

        @ApiModelProperty(value = "默认角色code")
        var roleCode: String? = null,

        @ApiModelProperty(value = "角色分类名")
        var roleTypeName: String? = null,

        @ApiModelProperty(value = "角色分类名")
        var orgConcatName: String = "",

        @ApiModelProperty(value = "角色分类名")
        var roleConcatName: String = ""

) : Account()
