package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Column
import javax.persistence.Table

@ApiModel(description = "角色")
@Table(name = "sys_role")
class Role(

        @ApiModelProperty(value = "角色分类ID", required = true)
        @NotBlank(message = "角色分类ID必填！")
        @Column(name = "role_type")
        var roleType: String? = "",

        @ApiModelProperty(value = "角色编码", required = true)
        @NotBlank(message = "角色编码必填！")
        @Column(name = "role_code")
        var roleCode: String? = "",

        @ApiModelProperty(value = "角色名称", required = true)
        @NotBlank(message = "角色名称必填！")
        @Column(name = "role_name")
        var roleName: String? = "",

        @ApiModelProperty(value = "角色描述", required = true)
        @Column(name = "role_desc")
        var roleDesc: String? = "",

        @ApiModelProperty(value = "角色级别", required = true)
        @NotBlank(message = "角色级别必填！")
        @Column(name = "role_level")
        var roleLevel: Int? = 2,

        @ApiModelProperty(value = "角色状态", required = true)
        @NotBlank(message = "角色状态必填！")
        @Column(name = "status")
        var status: Int? = 1,

        @ApiModelProperty(value = "是否默认角色", required = true)
        @NotBlank(message = "是否默认角色必填！")
        @Column(name = "role_default")
        var roleDefault: Boolean = false

) : BaseDomain()