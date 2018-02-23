package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.Column
import javax.persistence.Table

@ApiModel(value = "角色与组织机构分配关系")
@Table(name = "system_role_org")
class RoleOrg(

        @ApiModelProperty(value = "组织机构id", required = true)
        @Column(name = "org_id")
        var orgId: String = "",

        @ApiModelProperty(value = "角色id", required = true)
        @Column(name = "role_id")
        var roleId: String = ""

) : BaseDomain()