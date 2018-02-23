package org.anowls.sys.domain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Column
import javax.persistence.Table

@ApiModel(description = "账户角色对应关系")
@Table(name = "sys_account_role")
open class AccountRole(

        @NotBlank(message = "用户id必填！")
        @ApiModelProperty(value = "用户id", required = true)
        @Column(name = "account_id")
        var accountId: String = "",

        @NotBlank(message = "角色id必填！")
        @ApiModelProperty(value = "角色id", required = true)
        @Column(name = "role_id")
        var roleId: String = ""

) : BaseDomain()
