package org.anowls.sys.domain.view

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.anowls.sys.domain.AccountRole

@ApiModel(description = "账户角色-视图对象")
data class AccountRoleVO(

        @ApiModelProperty(name = "登录名")
        var loginName: String = "",

        @ApiModelProperty(name = "用户名")
        var userName: String = "",

        @ApiModelProperty(name = "角色名")
        var roleName: String = "",

        @ApiModelProperty(name = "机构id")
        var orgId: String = "",

        @ApiModelProperty(name = "机构名")
        var orgName: String = "",

        @ApiModelProperty(name = "基本角色名")
        var baseRoleName: String = "",

        @ApiModelProperty(name = "是否默认角色")
        var defaultRole: Boolean = false
) : AccountRole()