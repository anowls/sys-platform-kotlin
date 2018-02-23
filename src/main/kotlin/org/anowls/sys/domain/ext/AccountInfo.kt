package org.anowls.sys.domain.ext

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.anowls.sys.domain.Account
import org.anowls.sys.domain.Organization
import org.anowls.sys.domain.Role
import javax.persistence.Transient


/**
 * <p>Title: sys-platform</p>
 * <p>Description: 用户信息-扩展</p>
 * <p>Copyright: Copyright © 2017-2020 汉博德信息技术有限公司 All Rights Reserved</p>
 * <p>Company: http://www.hanboard.com</p>
 *
 * @author haopeisong
 * @date 2018/1/26 0026
 */
@ApiModel(description = "用户信息-扩展")
data class AccountInfo(

        @ApiModelProperty(value = "默认机构")
        @Transient
        var organization: Organization? = null,

        @ApiModelProperty(value = "默认角色")
        @Transient
        var defaultRole: Role? = null,

        @ApiModelProperty(value = "默认角色ID")
        @Transient
        var roleId: String? = "",

        @ApiModelProperty(value = "角色分类码")
        @Transient
        var shortCode: String? = "",

        @ApiModelProperty(value = "角色码")
        @Transient
        var roleCode: String? = "",

        @ApiModelProperty(value = "角色分类集合")
        @Transient
        var shortCodes: List<String>? = null,

        @ApiModelProperty(value = "默认角色集合")
        @Transient
        var defaultRoles: List<Role>? = null

) : Account()
