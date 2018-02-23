package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.Column
import javax.persistence.Table

@ApiModel(description = "用户上下级机构权限关系")
@Table(name = "sys_account_org_authority")
class AccountOrgAuthority(

        @ApiModelProperty(value = "用户id", required = true)
        @Column(name = "account_id")
        var accountId: String? = null,

        @ApiModelProperty(value = "组织机构id", required = true)
        @Column(name = "upper_org_id")
        var upperOrgId: String? = null,

        @ApiModelProperty(value = "是否有权限查看该机构节点的人员", required = true)
        @Column(name = "org_authority")
        var orgAuthority: Boolean? = null,

        @ApiModelProperty(value = "是否默认机构", required = true)
        @Column(name = "org_default")
        var orgDefault: Boolean? = null

) : BaseDomain()